package ru.levin.tmws.server.exception;

import org.jetbrains.annotations.NotNull;

public class AccessForbiddenException extends RuntimeException {

    @Override
    @NotNull
    public String getMessage() {
        return "Access denied. Please contact the administrator.";
    }

}
