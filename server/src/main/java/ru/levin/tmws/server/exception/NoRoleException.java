package ru.levin.tmws.server.exception;

import org.jetbrains.annotations.NotNull;

public class NoRoleException extends RuntimeException {

    @Override
    @NotNull
    public String getMessage() {
        return "Illegal state of entity: role is null.";
    }

}
