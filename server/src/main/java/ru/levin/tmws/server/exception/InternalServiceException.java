package ru.levin.tmws.server.exception;

import org.jetbrains.annotations.NotNull;

public class InternalServiceException extends RuntimeException {

    @Override
    @NotNull
    public String getMessage() {
        return "Internal service exception. Please contact the administrator.";
    }

}
