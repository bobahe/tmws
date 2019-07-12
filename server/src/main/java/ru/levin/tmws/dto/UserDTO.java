package ru.levin.tmws.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.entity.RoleType;

import javax.persistence.*;

@Entity
@Table(name = "app_user")
public class UserDTO extends AbstractDTO {

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
    @Enumerated(value = EnumType.STRING)
    @Nullable
    private RoleType roleType = RoleType.USER;

    @Override
    public @Nullable String getId() {
        return this.id;
    }

    @Override
    public void setId(final @NotNull String id) {
        this.id = id;
    }

}
