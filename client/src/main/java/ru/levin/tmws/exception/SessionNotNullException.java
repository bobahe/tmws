package ru.levin.tmws.exception;

import org.jetbrains.annotations.NotNull;

public class SessionNotNullException extends RuntimeException {

    @Override
    @NotNull
    public String getMessage() {
        return "You have to logout first.";
    }

}
