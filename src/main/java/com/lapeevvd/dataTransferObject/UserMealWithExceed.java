package com.lapeevvd.dataTransferObject;

import com.lapeevvd.model.UserMeal;

public class UserMealWithExceed extends UserMeal{

    protected final boolean exceed;

    public UserMealWithExceed(UserMeal userMeal, boolean exceed) {
        super(userMeal.getId(), userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories());
        this.exceed = exceed;
    }

    public boolean isExceed() {
        return exceed;
    }
}
