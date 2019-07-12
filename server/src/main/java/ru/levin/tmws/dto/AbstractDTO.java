package ru.levin.tmws.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractDTO implements Serializable {

    @Id
    @Column(length = 191)
    @Nullable
    protected String id;

    @Nullable
    public abstract String getId();

    public abstract void setId(@NotNull final String id);

}
