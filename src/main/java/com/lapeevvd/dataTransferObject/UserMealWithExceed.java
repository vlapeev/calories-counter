package com.lapeevvd.dataTransferObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lapeevvd.model.UserMeal;

import java.time.LocalDateTime;

public class UserMealWithExceed extends UserMeal{

    protected final boolean exceed;

    public UserMealWithExceed(@JsonProperty("id")Integer id,
                              @JsonProperty("dateTime")LocalDateTime dateTime,
                              @JsonProperty("description")String description,
                              @JsonProperty("calories")int calories,
                              @JsonProperty("exceed") boolean exceed) {
        super(id, dateTime, description, calories);
        this.exceed = exceed;
    }

    public boolean isExceed() {
        return exceed;
    }

    @Override
    public String toString() {
        return "UserMealWithExceed{" +
                "exceed=" + exceed +
                "} " + super.toString();
    }
}
