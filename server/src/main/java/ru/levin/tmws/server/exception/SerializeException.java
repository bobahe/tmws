package ru.levin.tmws.server.exception;

import org.jetbrains.annotations.NotNull;

public class SerializeException extends RuntimeException {

    @Override
    @NotNull
    public String getMessage() {
        return "Can't serialize data into file.";
    }

}
