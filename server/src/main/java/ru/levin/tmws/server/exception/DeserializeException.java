package ru.levin.tmws.server.exception;

import org.jetbrains.annotations.NotNull;

public class DeserializeException extends RuntimeException {

    @Override
    @NotNull
    public String getMessage() {
        return "Can't deserialize data from file.";
    }

}
