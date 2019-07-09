package ru.levin.tmws.server.exception;

import org.jetbrains.annotations.NotNull;

public class NoSelectedProjectException extends RuntimeException {

    @Override
    @NotNull
    public String getMessage() {
        return "Selected project is null. Try to select one.";
    }

}
