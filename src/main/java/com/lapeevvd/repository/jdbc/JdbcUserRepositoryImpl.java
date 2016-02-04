package com.lapeevvd.repository.jdbc;

import com.lapeevvd.model.Role;
import com.lapeevvd.model.User;
import com.lapeevvd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Transactional(readOnly = true)
public class JdbcUserRepositoryImpl implements UserRepository {

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertUser;


    @Autowired
    public JdbcUserRepositoryImpl(DataSource dataSource) {
        this.insertUser = new SimpleJdbcInsert(dataSource).withTableName("USERS").usingGeneratedKeyColumns("id");
    }

    @Override
    @Transactional
    public User save(User user) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("name", user.getName())
                .addValue("password", user.getPassword())
                .addValue("email", user.getEmail())
                .addValue("registered", user.getRegistered())
                .addValue("enabled", user.isEnabled())
                .addValue("caloriesPerDay", user.getCaloriesPerDay());

        if (user.isNew()){
            Number newKey = insertUser.executeAndReturnKey(map);
            user.setId(newKey.intValue());
            insertRoles(user);
        } else {
            deleteRoles(user);
            insertRoles(user);
            namedParameterJdbcTemplate.update(
                    "UPDATE users SET name=:name, email=:email, password=:password, " +
                            "registered=:registered, enabled=:enabled, calories_per_day=:caloriesPerDay WHERE id=:id", map);
        }
        return user;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
    }

    @Override
    public User get(int id) {
        List<User> list = jdbcTemplate.query("SELECT * FROM users WHERE id=?" ,ROW_MAPPER, id);
        return setRoles(DataAccessUtils.singleResult(list));
    }

    @Override
    public User getByEmail(String email) {
        return setRoles(jdbcTemplate.queryForObject("SELECT * FROM users WHERE email=?", ROW_MAPPER, email));
    }

    @Override
    public List<User> getAll() {
        List<User> users = jdbcTemplate.query("SELECT * FROM users ORDER BY name, email", ROW_MAPPER);

        class UserRole {
            public UserRole(Role role, int userId) {
                this.role = role;
                this.userId = userId;
            }

            final private Role role;
            final private int userId;

            public Role getRole() {
                return role;
            }

            public int getUserId() {
                return userId;
            }
        }

        Map<Integer, List<UserRole>> userRoles = jdbcTemplate.query("SELECT role, user_id FROM user_roles",
                (rs, rowNum) -> new UserRole(Role.valueOf(rs.getString("role")), rs.getInt("user_id")))
                .stream().collect(Collectors.groupingBy(UserRole::getUserId));

        users.forEach(u -> u.setRoles(userRoles.get(u.getId()).stream().map(UserRole::getRole).collect(Collectors.toList())));
        return users;
    }

    private void insertRoles(User user) {
        Set<Role> roles = user.getRoles();
        Iterator<Role> iterator = roles.iterator();

        jdbcTemplate.batchUpdate("INSERT INTO user_roles (user_id, role) VALUES (?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, user.getId());
                        ps.setString(2, iterator.next().name());
                    }

                    @Override
                    public int getBatchSize() {
                        return roles.size();
                    }
                });
    }

    private void deleteRoles(User user) {
        jdbcTemplate.update("DELETE FROM user_roles WHERE user_id=?", user.getId());
    }

    private User setRoles(User user) {
        if (user != null) {
            List<Role> roles = jdbcTemplate.query("SELECT role FROM user_roles  WHERE user_id=?",
                    (rs, rowNum) -> Role.valueOf(rs.getString("role")), user.getId());
            user.setRoles(roles);
        }
        return user;
    }
}
