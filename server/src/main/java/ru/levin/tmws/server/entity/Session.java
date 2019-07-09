package ru.levin.tmws.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public final class Session extends AbstractHasOwnerEntity implements Serializable {

    @Nullable
    private String id;

    @Nullable
    private String signature;

    @Override
    public @Nullable String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(final @NotNull String userId) {
        this.userId = userId;
    }

}
