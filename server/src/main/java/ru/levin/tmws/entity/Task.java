package ru.levin.tmws.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.IContainsDatesAndStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@NamedEntityGraph(
        name = "task-graph",
        attributeNodes = {
                @NamedAttributeNode("project"),
                @NamedAttributeNode("user")
        }
)
@Entity
@Table(name = "app_task")
public final class Task extends AbstractHasOwnerEntity implements IContainsDatesAndStatus, Serializable {

    @Column
    @Nullable
    private String name;

    @Column
    @Nullable
    private String description;

    @ManyToOne(cascade = {CascadeType.REMOVE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Nullable
    private Project project;

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

    public Task() {
        this.id = UUID.randomUUID().toString();
    }

    @Nullable
    public String getId() {
        return id;
    }

    public void setId(@NotNull final String id) {
        this.id = id;
    }

    @Nullable
    public User getUser() {
        return this.user;
    }

    public void setUser(@NotNull final User user) {
        this.user = user;
    }

}
