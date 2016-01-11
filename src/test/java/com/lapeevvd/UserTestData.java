package com.lapeevvd;

import com.lapeevvd.model.AbstractEntity;
import com.lapeevvd.model.Role;
import com.lapeevvd.model.User;

public class UserTestData {
    public static int USER_ID = AbstractEntity.START_SEQ;
    public static int ADMIN_ID = AbstractEntity.START_SEQ +1;

    public static final User USER = new User(USER_ID, "User", "password", "user@yandex.ru", Role.USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin", "admin@gmail.com", Role.ADMIN);

}
