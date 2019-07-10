package ru.levin.tmws.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public final class Session extends AbstractHasOwnerEntity implements Serializable {

    @Nullable
    private String id;

    @Nullable
    private String signature;

    @Nullable
    private Long timestamp;

    public Session() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public @Nullable String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(final @NotNull String userId) {
        this.userId = userId;
    }

}
