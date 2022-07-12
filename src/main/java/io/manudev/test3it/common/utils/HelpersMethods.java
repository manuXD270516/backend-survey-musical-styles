package io.manudev.test3it.common.utils;

import java.util.regex.Pattern;

public class HelpersMethods {

    private static final String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";


    public static <T> boolean isNull(T object) {
        return object == null;
    }

    public static boolean emailValid(String email) {
        return Pattern.compile(EMAIL_PATTERN)
                .matcher(email)
                .matches();
    }

    public static boolean passwordValid(String password) {
        return Pattern.compile(PASSWORD_PATTERN)
                .matcher(password)
                .matches();
    }

}
