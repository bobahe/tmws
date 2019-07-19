package ru.levin.tmws.entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractHasOwnerEntity extends AbstractEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @Nullable
    protected User user;

    @Nullable
    public abstract User getUser();

    public abstract void setUser(@NotNull final User user);

}
