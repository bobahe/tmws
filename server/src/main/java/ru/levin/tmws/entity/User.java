package ru.levin.tmws.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "app_user")
public final class User extends AbstractEntity implements Serializable {

    @Column
    @Nullable
    private String email;

    @Column
    @Nullable
    private String firstName;

    @Column
    @Nullable
    private String lastName;

    @Column
    @Nullable
    private Boolean locked;

    @Column(unique = true)
    @Nullable
    private String login;

    @Column
    @Nullable
    private String middleName;

    @Column
    @Nullable
    private String password;

    @Column
    @Nullable
    private String phone;

    @Column(name = "role")
    @Enumerated
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
