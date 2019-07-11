package ru.levin.tmws.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "app_user")
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
