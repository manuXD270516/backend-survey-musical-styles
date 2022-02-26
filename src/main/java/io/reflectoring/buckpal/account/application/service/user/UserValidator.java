package io.reflectoring.buckpal.account.application.service.user;

import io.reflectoring.buckpal.account.application.port.in.user.CreateUserCommand;
import io.reflectoring.buckpal.common.utils.HelpersMethods;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {

    public static List<String> validateCreateUserCommand(CreateUserCommand createUserCommand){
        List<String> errors= new ArrayList<>();
         if (!HelpersMethods.emailValid(createUserCommand.getEmail())){
             errors.add("Email invalido");
         }
         if (!HelpersMethods.passwordValid(createUserCommand.getPassword())){
             errors.add("Password invalido, Password must contain at least one digit [0-9].\n" +
                     "Password must contain at least one lowercase Latin character [a-z].\n" +
                     "Password must contain at least one uppercase Latin character [A-Z].\n" +
                     "Password must contain at least one special character like ! @ # & ( ).\n" +
                     "Password must contain a length of at least 8 characters and a maximum of 20 characters.");
         }
         return errors;
    }
}
