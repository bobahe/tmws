package ru.levin.tmws.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.IContainsDatesAndStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "app_project")
public final class Project extends AbstractHasOwnerEntity implements IContainsDatesAndStatus, Serializable {

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
    @Enumerated
    @Nullable
    private Status status = Status.PLANNED;

    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @Nullable
    private List<Task> tasks;

    public Project() {
        this.id = UUID.randomUUID().toString();
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable final String description) {
        this.description = description;
    }

    @Override
    public @Nullable String getId() {
        return this.id;
    }

    @Override
    public void setId(final @NotNull String id) {
        this.id = id;
    }

    @Override
    public @Nullable User getUser() {
        return this.user;
    }

    @Override
    public void setUser(final @NotNull User user) {
        this.user = user;
    }

}
