package com.lapeevvd.model;

import com.lapeevvd.util.UserMealUtil;

import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

public class User extends NamedEntity {

    protected String password;
    protected String email;
    protected Date registered = new Date();
    protected boolean enabled;
    protected Set<Role> roles;
    protected int caloriesPerDay;

    public User(Integer id, String name, String password, String email, Role role, Role... roles) {
        this(id, name, password, email, true, EnumSet.of(role, roles), UserMealUtil.DEFAULT_CALORIES_PER_DAY);
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

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getCaloriesPerDay() {
        return caloriesPerDay;
    }

    public void setCaloriesPerDay(int caloriesPerDay) {
        this.caloriesPerDay = caloriesPerDay;
    }
}
