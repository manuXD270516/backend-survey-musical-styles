package io.manudev.test3it.core.application.service.user.validator;

import io.manudev.test3it.core.application.port.in.user.CreateUserCommand;
import io.manudev.test3it.core.application.port.in.userMusicStyle.CreateUserMusicStyleCommand;
import io.manudev.test3it.common.utils.HelpersMethods;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {

    public static List<String> validateCreateUserCommand(CreateUserCommand createUserCommand) {
        List<String> errors = new ArrayList<>();
        if (!HelpersMethods.emailValid(createUserCommand.getEmail())) {
            errors.add("Email invalido");
        }
        if (!HelpersMethods.passwordValid(createUserCommand.getPassword())) {
            errors.add("Password invalido, Password must contain at least one digit [0-9].\n" +
                    "Password must contain at least one lowercase Latin character [a-z].\n" +
                    "Password must contain at least one uppercase Latin character [A-Z].\n" +
                    "Password must contain at least one special character like ! @ # & ( ).\n" +
                    "Password must contain a length of at least 8 characters and a maximum of 20 characters.");
        }
        return errors;
    }

    public static List<String> validateCreateUserMusicStyleCommand(CreateUserMusicStyleCommand createUserMusicStyleCommand) {
        List<String> errors = new ArrayList<>();
        if (createUserMusicStyleCommand.getUserId().isEmpty() || createUserMusicStyleCommand.getUserId().isBlank()) {
            errors.add("UserId no encontrado");
        }
        if (createUserMusicStyleCommand.getMusicalStyleId().isEmpty() || createUserMusicStyleCommand.getMusicalStyleId().isBlank()) {
            errors.add("MusicStyleId no encontrado");
        }
        return errors;
    }
}
