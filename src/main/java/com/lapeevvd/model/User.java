package com.lapeevvd.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(name = "user_unique_email_idx", columnNames = "email")})
@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.BY_EMAIL, query = "SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email=:email"),
        @NamedQuery(name = User.ALL_SORTED, query = "SELECT DISTINCT(u) FROM User u LEFT JOIN FETCH u.roles ORDER BY u.name, u.email")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User extends NamedEntity {

    public static final String DELETE = "User.delete";
    public static final String BY_EMAIL = "User.getByEmail";
    public static final String ALL_SORTED = "User.getAllSorted";

    @Column(name = "password", nullable = false)
    @NotEmpty
    @Length(min = 5)
    protected String password;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotEmpty
    @Size(min = 6, message = " must be greater than  characters")
    protected String email;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    protected Date registered = new Date();

    @Column(name = "enabled", nullable = false)
    protected boolean enabled;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    protected Set<Role> roles;

    @Column(name = "calories_per_day", columnDefinition = "default 2000")
    @Digits(fraction = 0, integer = 4)
    @NotNull(message = " may be not empty")
    protected Integer caloriesPerDay;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    @OrderBy("dateTime DESC")
    //@JsonIgnore
    protected List<UserMeal> meals;

    public User() {
        super();
    }

    // под тесты
    public User(User u) {
        this(u.getId(), u.getName(), u.getPassword(), u.getEmail(), u.isEnabled(), u.getRoles(), u.getCaloriesPerDay());
    }

    public User(Integer id, String name, String password, String email, Integer caloriesPerDay, Role role, Role... roles) {
        this(id, name, password, email, true, EnumSet.of(role, roles), caloriesPerDay);
    }

    public User(Integer id, String name, String password, String email, boolean enabled, Set<Role> roles, int caloriesPerDay) {
        super(id, name);
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.roles = roles;
        this.caloriesPerDay = caloriesPerDay;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = EnumSet.copyOf(roles);
    }

    public int getCaloriesPerDay() {
        return caloriesPerDay;
    }

    public void setCaloriesPerDay(Integer caloriesPerDay) {
        this.caloriesPerDay = caloriesPerDay;
    }

    public List<UserMeal> getMeals() {
        return meals;
    }

    @Override
    public String toString() {
        return "User (" +
                "id=" + id +
                ", email=" + email +
                ", name=" + name +
                ", enabled=" + enabled +
                ", roles=" + roles +
                ", caloriesPerDay=" + caloriesPerDay +
                ')';
    }
}
