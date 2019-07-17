package ru.levin.tmws.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "app_session")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SessionDTO extends AbstractHasOwnerDTO {

    public SessionDTO() {
        this.id = UUID.randomUUID().toString();
    }

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
