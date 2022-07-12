package io.manudev.test3it.core.domain.exception;

public class NotDataFoundException extends RuntimeException{

    public NotDataFoundException(String message) {
        super(message);
    }

    public NotDataFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
