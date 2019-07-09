package ru.levin.tmws.client.exception;

import org.jetbrains.annotations.NotNull;

public class CommandNotFoundException extends RuntimeException {

    @Override
    @NotNull
    public String getMessage() {
        return "There is no such command.";
    }

}
