package ru.levin.tmws.server.exception;

import org.jetbrains.annotations.NotNull;

public class NoSelectedTaskException extends RuntimeException {

    @Override
    @NotNull
    public String getMessage() {
        return "Selected task is null. Try to select one.";
    }

}
