package ru.levin.tmws.exception;

import org.jetbrains.annotations.NotNull;

public class NullSessionException extends RuntimeException {

    @Override
    @NotNull
    public String getMessage() {
        return "Session is null. Try to open new session.";
    }

}
