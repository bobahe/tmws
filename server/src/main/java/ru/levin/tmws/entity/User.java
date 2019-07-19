package ru.levin.tmws.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NamedEntityGraph(
        name = "user-graph",
        attributeNodes = {
                @NamedAttributeNode("projects"),
                @NamedAttributeNode("tasks")
        }
)
@Entity
@Table(name = "app_user")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public final class User extends AbstractEntity implements Serializable {

    @Column
    @JsonIgnore
    @Nullable
    private String email;

    @Column
    @JsonIgnore
    @Nullable
    private String firstName;

    @Column
    @JsonIgnore
    @Nullable
    private String lastName;

    @Column
    @JsonIgnore
    @Nullable
    private Boolean locked;

    @Column(unique = true)
    @JsonIgnore
    @Nullable
    private String login;

    @Column
    @JsonIgnore
    @Nullable
    private String middleName;

    @Column
    @JsonIgnore
    @Nullable
    private String password;

    @Column
    @JsonIgnore
    @Nullable
    private String phone;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    @JsonIgnore
    @Nullable
    private RoleType roleType = RoleType.USER;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @NotNull
    @JsonIgnore
    private List<Project> projects;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @NotNull
    @JsonIgnore
    private List<Task> tasks;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @NotNull
    @JsonIgnore
    private List<Session> sessions;

    public User() {
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

}
