package ru.levin.tmws.server.exception;

import org.jetbrains.annotations.NotNull;

public class DbConnectionException extends RuntimeException {

    @Override
    @NotNull
    public String getMessage() {
        return "Can't connect to MySql server. Application will be closed.";
    }

}
