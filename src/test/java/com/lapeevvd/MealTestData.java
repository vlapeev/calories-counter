package com.lapeevvd;

import com.lapeevvd.matcher.ModelMatcher;
import com.lapeevvd.model.AbstractEntity;
import com.lapeevvd.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;

public class MealTestData {

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

    public static final int MEAL_ID = AbstractEntity.START_SEQ + 2;
    public static final int ADMIN_MEAL_ID = AbstractEntity.START_SEQ + 8;

    public static final UserMeal MEAL1 = new UserMeal(MEAL_ID, LocalDateTime.of(2016, Month.JANUARY, 15, 10, 0), "Завтрак", 500);
    public static final UserMeal MEAL2 = new UserMeal(MEAL_ID + 1, LocalDateTime.of(2016, Month.JANUARY, 15, 13, 0), "Обед", 1000);
    public static final UserMeal MEAL3 = new UserMeal(MEAL_ID + 2, LocalDateTime.of(2016, Month.JANUARY, 15, 20, 0), "Ужин", 500);
    public static final UserMeal MEAL4 = new UserMeal(MEAL_ID + 3, LocalDateTime.of(2016, Month.JANUARY, 16, 10, 0), "Завтрак", 500);
    public static final UserMeal MEAL5 = new UserMeal(MEAL_ID + 4, LocalDateTime.of(2016, Month.JANUARY, 16, 13, 0), "Обед", 1000);
    public static final UserMeal MEAL6 = new UserMeal(MEAL_ID + 5, LocalDateTime.of(2016, Month.JANUARY, 16, 20, 0), "Ужин", 510);
    public static final UserMeal ADMIN_MEAL = new UserMeal(ADMIN_MEAL_ID, LocalDateTime.of(2016, Month.JANUARY, 4, 14, 0), "Админ ланч", 510);
    public static final UserMeal ADMIN_MEAL2 = new UserMeal(ADMIN_MEAL_ID + 1, LocalDateTime.of(2016, Month.JANUARY, 4, 21, 0), "Админ ужин", 1500);

    public static final List<UserMeal> USER_MEALS = Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);

    public static UserMeal getCreated() {
        return new UserMeal(null, of(2016, Month.JUNE, 1, 18, 0), "Созданный ужин", 300);
    }

    public static UserMeal getUpdated() {
        return new UserMeal(MEAL_ID, MEAL1.getDateTime(), "Обновленный завтрак", 200);
    }

}
