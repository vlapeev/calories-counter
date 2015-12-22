package com.lapeevvd.model;

import java.time.LocalDateTime;

public class UserMeal extends AbstractEntity {

    protected final LocalDateTime dateTime;
    protected final String description;
    protected final int calories;

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
}
