package com.lapeevvd;

import com.lapeevvd.matcher.ModelMatcher;
import com.lapeevvd.model.Role;
import com.lapeevvd.model.User;
import com.lapeevvd.util.UserMealUtil;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class TestUser extends User{

    public static final ModelMatcher<User, TestUser> MATCHER = new ModelMatcher<>(user -> ((user instanceof TestUser) ? (TestUser) user : new TestUser(user)));

    public TestUser(User u){
        this(u.getId(), u.getName(), u.getPassword(), u.getEmail(), u.isEnabled(), u.getRoles(), u.getCaloriesPerDay());
    }

    public TestUser(String name, String password, String email, Role role, Role... roles) {
        this(null, name, password, email, true, EnumSet.of(role, roles), UserMealUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public TestUser(Integer id, String name, String password, String email, boolean enabled, Set<Role> roles, int caloriesPerDay) {
        super(id, name, password, email, enabled, roles, caloriesPerDay);
    }

    public User asUser(){
        return new User(this);
    }

    @Override
    public String toString() {
        return "User (" +
                "id=" + id +
                ", email=" + email +
                ", name=" + name +
                ", enabled=" + enabled +
                ", password=" + password +
                ", authorities=" + roles +
                ')';
    }

   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestUser that = (TestUser) o;
        return Objects.equals(this.password, that.password)
                && Objects.equals(this.id, that.id)
                && Objects.equals(this.name, that.name)
                && Objects.equals(this.email, that.email)
                && Objects.equals(this.caloriesPerDay, that.caloriesPerDay)
                && Objects.equals(this.enabled, that.enabled);
    }
}