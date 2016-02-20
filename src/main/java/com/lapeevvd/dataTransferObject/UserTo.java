package com.lapeevvd.dataTransferObject;

import com.lapeevvd.util.UserUtil;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class UserTo implements Serializable{
    static final long serialVersionUID = 1L;

    protected Integer id;

    @NotEmpty
    protected String name;

    @Size(min = 5, max = 20, message = " must be between 5 and 20 characters")
    protected String password;

    @NotEmpty
    @Email
    @Size(min = 6, message = " must be greater than 6 characters")
    protected String email;

    @Range(min = 500, max = 4000)
    @NotNull(message = " may be not empty")
    protected Integer caloriesPerDay = UserUtil.DEFAULT_CALORIES_PER_DAY;;

    public UserTo() {
    }

    public UserTo(Integer id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public UserTo(Integer id, String name, String email, String password, int caloriesPerDay) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.caloriesPerDay = caloriesPerDay;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = (id == null ? 0 : id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getCaloriesPerDay() {
        return caloriesPerDay;
    }

    public void setCaloriesPerDay(Integer caloriesPerDay) {
        this.caloriesPerDay = caloriesPerDay;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", caloriesPerDay=" + caloriesPerDay +
                '}';
    }
}
