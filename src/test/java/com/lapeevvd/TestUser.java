package com.lapeevvd;

import com.lapeevvd.matcher.ModelMatcher;
import com.lapeevvd.model.Role;
import com.lapeevvd.model.User;
import com.lapeevvd.util.PasswordUtil;
import com.lapeevvd.util.UserUtil;
import com.lapeevvd.util.logger.LoggerWrapper;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class TestUser extends User{
    private static final LoggerWrapper LOG = LoggerWrapper.get(TestUser.class);

    /*public static final ModelMatcher<User, TestUser> MATCHER = new ModelMatcher<>(user -> ((user instanceof TestUser) ? (TestUser) user : new TestUser(user)));*/
    public static final ModelMatcher<User, TestUser> MATCHER = new ModelMatcher<>(user -> ((user instanceof TestUser) ? (TestUser) user : new TestUser(user)), User.class);

    public TestUser(User u){
        this(u.getId(), u.getName(), u.getPassword(), u.getEmail(), u.isEnabled(), u.getRoles(), u.getCaloriesPerDay());
    }

    public TestUser(String name, String password, String email, int caloriesPerDay, Role role, Role... roles) {
        this(null, name, password, email, true, EnumSet.of(role, roles), caloriesPerDay);
    }

    public TestUser(Integer id, String name, String password, String email, boolean enabled, Set<Role> roles, int caloriesPerDay) {
        super(id, name, password, email, enabled, roles, caloriesPerDay);
    }

    public User asUser(){
        return UserUtil.prepareToSave(new User(this));
    }

    @Override
    public String toString() {
        return "User (" +
                "id=" + id +
                ", name=" + name +
                ", password=" + password +
                ", email=" + email +
                ", enabled=" + enabled +
                ", authorities=" + roles +
                ", caloriesPerDay=" + caloriesPerDay +
                ')';
    }

   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestUser that = (TestUser) o;
        return comparePassword(this.password, that.password)
                && Objects.equals(this.id, that.id)
                && Objects.equals(this.name, that.name)
                && Objects.equals(this.email, that.email)
                && Objects.equals(this.caloriesPerDay, that.caloriesPerDay)
                && Objects.equals(this.enabled, that.enabled)
                && Objects.equals(this.roles, that.roles);
    }

    private static boolean comparePassword(String rawPassword, String password) {
        if (PasswordUtil.isEncoded(rawPassword)) {
            LOG.warn("Expected password couldn't be compared with actual");
        } else if (!PasswordUtil.isMatch(rawPassword, password)) {
            LOG.error("Password " + password + " doesn't match encoded " + password);
            return false;
        }
        return true;
    }
}