package com.lapeevvd.util;

public class LoggedUser {
    private static int id = 1;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        LoggedUser.id = id;
    }
}
