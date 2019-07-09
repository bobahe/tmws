package ru.levin.tmws.exception;

import org.jetbrains.annotations.NotNull;

public class SelectException extends RuntimeException {

    @Override
    @NotNull
    public String getMessage() {
        return "Could not select entity";
    }

}
