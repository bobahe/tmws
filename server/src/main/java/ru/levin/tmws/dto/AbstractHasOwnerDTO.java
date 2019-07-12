package ru.levin.tmws.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractHasOwnerDTO extends AbstractDTO implements Serializable {

    @Column(name = "user_id")
    @Nullable
    protected String userId;

    @Nullable
    public abstract String getUserId();

    public abstract void setUserId(@NotNull final String userId);

}
