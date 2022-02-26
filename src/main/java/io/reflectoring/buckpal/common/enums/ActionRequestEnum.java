package io.reflectoring.buckpal.common.enums;

public enum ActionRequestEnum {

    CREATE("Registro", 1000),
    PREVIOUS_CREATE("Previo Registro", 1001),
    OVERWRITE("Sobreescribir informacion", 1002),

    UPDATE("Modificacion", 2000),
    DELETE("Eliminacion", 3000),

    RESOURCE_NOT_FOUND("Recurso no encontrado", 5000),
    RESOURCE_ALREADY_EXISTS("Recurso ya existente, no se puede crear nuevamente", 6000),
    VALIDATION_ERROR("Errores de validacion", 7000);


    private String action;
    private int identifier;

    public int getIdentifier() {
        return identifier;
    }

    public String getAction() {
        return action;
    }

    ActionRequestEnum(String action, int identifier) {
        this.action = action;
        this.identifier = identifier;
    }
}
