package com.lapeevvd.util;

public class LoggedUser {
    private static int id = 100000;
    private static int caloriesPerDay = 2000;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        LoggedUser.id = id;
    }

    public static int getCaloriesPerDay() {
        return caloriesPerDay;
    }

    public static void setCaloriesPerDay(int caloriesPerDay) {
        LoggedUser.caloriesPerDay = caloriesPerDay;
    }
}
