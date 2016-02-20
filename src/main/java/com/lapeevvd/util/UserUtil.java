package com.lapeevvd.util;

import com.lapeevvd.dataTransferObject.UserTo;
import com.lapeevvd.model.Role;
import com.lapeevvd.model.User;

public class UserUtil {
    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static User createFromUserTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getPassword(), userTo.getEmail().toLowerCase(), userTo.getCaloriesPerDay(), Role.ROLE_USER);
    }

    public static User updateFromUserTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail());
        user.setPassword(userTo.getPassword());
        user.setCaloriesPerDay(userTo.getCaloriesPerDay());
        return prepareToSave(user);
    }

    public static User prepareToSave(User user){
        user.setPassword(PasswordUtil.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getCaloriesPerDay());
    }
}
