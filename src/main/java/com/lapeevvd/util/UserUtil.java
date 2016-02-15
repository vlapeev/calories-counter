package com.lapeevvd.util;

import com.lapeevvd.dataTransferObject.UserTo;
import com.lapeevvd.model.Role;
import com.lapeevvd.model.User;
import org.springframework.util.StringUtils;

public class UserUtil {
    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static User createFromUserTo(UserTo userTo){
        return new User(null, userTo.getName(), userTo.getPassword(), userTo.getEmail(), Role.ROLE_USER);
    }

    public static User updateFromUserTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        String password = userTo.getPassword();
        if (!StringUtils.isEmpty(password)) {
            user.setPassword(password);
        }
        return user;
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getCaloriesPerDay());
    }
}
