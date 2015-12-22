package com.lapeevvd.dataTransferObject;

import com.lapeevvd.model.UserMeal;

import java.time.LocalDateTime;

public class UserMealWithExceed extends UserMeal{

    protected final boolean exceed;

    public UserMealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed) {
        super(dateTime, description, calories);
        this.exceed = exceed;
    }

    public boolean isExceed() {
        return exceed;
    }
}
