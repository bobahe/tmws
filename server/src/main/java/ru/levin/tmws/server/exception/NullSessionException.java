package ru.levin.tmws.server.exception;

import org.jetbrains.annotations.NotNull;

public class NullSessionException extends RuntimeException {

    @Override
    @NotNull
    public String getMessage() {
        return "Session is null. Try to open new session.";
    }

}
