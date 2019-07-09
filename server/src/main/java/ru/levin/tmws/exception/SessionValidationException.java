package ru.levin.tmws.exception;

import org.jetbrains.annotations.NotNull;

public class SessionValidationException extends RuntimeException {

    @Override
    @NotNull
    public String getMessage() {
        return "Session is not valid. Try to open new session.";
    }

}
