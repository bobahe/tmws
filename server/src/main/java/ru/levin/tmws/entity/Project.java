package ru.levin.tmws.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.IContainsDatesAndStatus;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NamedEntityGraph(
        name = "project-graph",
        attributeNodes = {
                @NamedAttributeNode("tasks"),
                @NamedAttributeNode("user")
        }
)
@Entity
@Table(name = "app_project")
@XmlAccessorType(XmlAccessType.NONE)
public final class Project extends AbstractHasOwnerEntity implements IContainsDatesAndStatus, Serializable {

    @Column
    @Nullable
    @XmlElement
    private String name;

    @Column
    @Nullable
    @XmlElement
    private String description;

    @Column
    @Nullable
    @XmlElement
    private Date startDate;

    @Column
    @Nullable
    @XmlElement
    private Date endDate;

    @Column
    @Enumerated(value = EnumType.STRING)
    @Nullable
    @XmlElement
    private Status status = Status.PLANNED;

    @OneToMany(
            mappedBy = "project",
            cascade = {CascadeType.REMOVE}
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @XmlTransient
    @JsonIgnore
    @Nullable
    private List<Task> tasks;

    public Project() {
        this.id = UUID.randomUUID().toString();
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
