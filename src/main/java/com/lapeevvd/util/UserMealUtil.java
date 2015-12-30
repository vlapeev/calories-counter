package com.lapeevvd.util;

import com.lapeevvd.dataTransferObject.UserMealWithExceed;
import com.lapeevvd.model.UserMeal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserMealUtil {
    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static final List<UserMeal> USER_MEAL_LIST = Arrays.asList(
            new UserMeal(LocalDateTime.of(2015, Month.DECEMBER, 22, 10, 0), "Завтрак", 500),
            new UserMeal(LocalDateTime.of(2015, Month.DECEMBER, 22, 13, 0), "Обед", 1000),
            new UserMeal(LocalDateTime.of(2015, Month.DECEMBER, 22, 20, 0), "Ужин", 500),
            new UserMeal(LocalDateTime.of(2015, Month.DECEMBER, 23, 10, 0), "Завтрак", 1000),
            new UserMeal(LocalDateTime.of(2015, Month.DECEMBER, 23, 13, 0), "Обед", 500),
            new UserMeal(LocalDateTime.of(2015, Month.DECEMBER, 23, 20, 0), "Ужин", 510)
    );

    public static final List<UserMeal> ADMIN_MEAL_LIST = Arrays.asList(
            new UserMeal(LocalDateTime.of(2015, Month.DECEMBER, 24, 14, 0), "Админ ланч", 510),
            new UserMeal(LocalDateTime.of(2015, Month.DECEMBER, 24, 21, 0), "Админ ужин", 1500)
    );

    public static UserMealWithExceed createWithExceed(UserMeal userMeal, boolean exceeded) {
        return new UserMealWithExceed(userMeal, exceeded);
    }

    //хитрый метод
    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = mealList.stream()
                .collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(),
                        Collectors.summingInt(UserMeal::getCalories)));

        return mealList.stream()
                .filter(userMeal -> TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime))
                .map(userMeal -> createWithExceed(userMeal, caloriesSumByDate.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}
