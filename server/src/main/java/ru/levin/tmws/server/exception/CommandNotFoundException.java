package ru.levin.tmws.server.exception;

import org.jetbrains.annotations.NotNull;

public class CommandNotFoundException extends RuntimeException {

    @Override
    @NotNull
    public String getMessage() {
        return "There is no such command.";
    }

}
