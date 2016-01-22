package com.lapeevvd.util.exception;

import com.lapeevvd.util.logger.LoggerWrapper;

public class CheckException {

    private static final LoggerWrapper LOG = LoggerWrapper.get(CheckException.class);

    public static void check(boolean found, int id) {
        check(found, "id = " + id);
    }

    public static <T> T check(T obj, int id) {
        return check(obj, "id = " + id);
    }

    public static <T> T check(T obj, String msg) {
        check(obj != null, msg);
        return obj;
    }

    // все методы обращаются к этому. При необходимости бросается исключение
    public static void check(boolean found, String msg) {
        if (!found) throw LOG.getNotFoundException("Not found entity with " + msg);
    }
}
