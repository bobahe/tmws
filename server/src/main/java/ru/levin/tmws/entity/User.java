package ru.levin.tmws.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public final class User extends AbstractEntity implements Serializable {

    @Nullable
    private String id;

    @Nullable
    private String email;

    @Nullable
    private String firstName;

    @Nullable
    private String lastName;

    @Nullable
    private Boolean locked;

    @Nullable
    private String login;

    @Nullable
    private String middleName;

    @Nullable
    private String password;

    @Nullable
    private String phone;

    @Nullable
    private RoleType roleType;

    public User() {
        this.id = UUID.randomUUID().toString();
    }
}
