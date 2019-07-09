package ru.levin.tmws.server.exception;

import org.jetbrains.annotations.NotNull;

public class SaveException extends RuntimeException {

    @Override
    @NotNull
    public String getMessage() {
        return "Could not save entity";
    }

}
