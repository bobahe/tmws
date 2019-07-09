package ru.levin.tmws.server.exception;

import org.jetbrains.annotations.NotNull;

public class UpdateException extends RuntimeException {

    @Override
    @NotNull
    public String getMessage() {
        return "Could not update entity";
    }

}
