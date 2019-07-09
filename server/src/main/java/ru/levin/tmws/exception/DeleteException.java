package ru.levin.tmws.exception;

import org.jetbrains.annotations.NotNull;

public class DeleteException extends RuntimeException {

    @Override
    @NotNull
    public String getMessage() {
        return "Could not remove entity";
    }

}
