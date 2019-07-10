package ru.levin.tmws.entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.UUID;

public abstract class AbstractEntity implements Serializable {

    @NotNull
    protected String id = UUID.randomUUID().toString();

    @Nullable
    public abstract String getId();

    public abstract void setId(@NotNull final String id);

}
