package ru.levin.tmws.server.exception;

import org.jetbrains.annotations.NotNull;

public class HashException extends RuntimeException {

    @Override
    @NotNull
    public String getMessage() {
        return "Could not hash the password";
    }

}
