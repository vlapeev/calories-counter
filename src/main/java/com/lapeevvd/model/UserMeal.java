package com.lapeevvd.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "meals", uniqueConstraints = @UniqueConstraint(name = "meals_unique_user_datetime_idx", columnNames = {"user_id", "date_time"}))
@NamedQueries({
        @NamedQuery(name = UserMeal.GET, query = "SELECT um FROM UserMeal um WHERE um.id=:id AND um.user.id=:userId"),
        @NamedQuery(name = UserMeal.ALL_SORTED, query = "SELECT um FROM UserMeal um WHERE um.user.id=:userId ORDER BY um.dateTime DESC"),
        @NamedQuery(name = UserMeal.GET_BETWEEN, query = "SELECT um FROM UserMeal um WHERE um.user.id=:userId " +
                "AND um.dateTime BETWEEN :startDate AND :endDate ORDER BY um.dateTime DESC"),
        @NamedQuery(name = UserMeal.DELETE, query = "DELETE FROM UserMeal um WHERE um.id=:id AND um.user.id=:userId")
})
public class UserMeal extends AbstractEntity {

    public static final String GET = "UserMeal.get";
    public static final String ALL_SORTED = "UserMeal.getAllMeal";
    public static final String GET_BETWEEN = "UserMeal.getBetween";
    public static final String DELETE = "UserMeal.delete";

    @Column(name = "date_time", nullable = false)
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    protected LocalDateTime dateTime;

    @Column(name = "description", nullable = false)
    @NotEmpty
    protected String description;

    @Column(name = "calories", nullable = false)
    @NotNull
    @Range(min = 10, max = 4000)
    protected int calories;

    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public UserMeal() { super();}

    public UserMeal(LocalDateTime dateTime, String description, int calories){
        this(null, dateTime, description, calories);
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "id=" + id +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
