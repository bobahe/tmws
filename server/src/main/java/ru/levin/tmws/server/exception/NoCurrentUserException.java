package ru.levin.tmws.server.exception;

import org.jetbrains.annotations.NotNull;

public class NoCurrentUserException extends RuntimeException {

    @Override
    @NotNull
    public String getMessage() {
        return "There is no current user. Try to log in.";
    }

}
