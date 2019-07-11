package ru.levin.tmws.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "app_session")
public final class Session extends AbstractHasOwnerEntity implements Serializable {

    @Column
    @Nullable
    private String signature;

    @Column
    @Nullable
    private Long timestamp = System.currentTimeMillis();

    public Session() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public @Nullable User getUser() {
        return this.user;
    }

    @Override
    public void setUser(final @NotNull User user) {
        this.user = user;
    }

    @Override
    public @Nullable String getId() {
        return this.id;
    }

    @Override
    public void setId(final @NotNull String id) {
        this.id = id;
    }

}
