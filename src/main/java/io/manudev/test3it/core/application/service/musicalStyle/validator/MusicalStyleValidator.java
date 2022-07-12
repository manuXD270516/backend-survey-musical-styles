package io.manudev.test3it.core.application.service.musicalStyle.validator;

import io.manudev.test3it.core.application.port.in.musicalStyle.CreateMusicalStyleCommand;

import java.util.ArrayList;
import java.util.List;

public class MusicalStyleValidator {

    public static List<String> validateCreateMusicalStyleCommand(CreateMusicalStyleCommand musicalStyleCommand) {
        List<String> errors = new ArrayList<>();
        if (musicalStyleCommand.getName().isBlank() || musicalStyleCommand.getDescription().isEmpty()) {
            errors.add("Name is required");
        }
        return errors;
    }
}
