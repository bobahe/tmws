package ru.levin.tmws.dto;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.entity.Status;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "app_project")
public class ProjectDTO extends AbstractHasOwnerDTO {

    public ProjectDTO() {
        this.id = UUID.randomUUID().toString();
    }

    @Column
    @Nullable
    private String name;

    @Column
    @Nullable
    private String description;

    @Column
    @Nullable
    private Date startDate;

    @Column
    @Nullable
    private Date endDate;

    @Column
    @Enumerated(value = EnumType.STRING)
    @Nullable
    private Status status = Status.PLANNED;

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
