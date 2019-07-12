package ru.levin.tmws.dto;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "app_session")
public class SessionDTO extends AbstractHasOwnerDTO {

    @Column
    @Nullable
    private String signature;

    @Column
    @Nullable
    private Long timestamp = System.currentTimeMillis();

    @Override
    public @Nullable String getUserId() {
        return this.userId;
    }

    @Override
    public void setUserId(final @NotNull String userId) {
        this.userId = userId;
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
