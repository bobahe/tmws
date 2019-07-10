package ru.levin.tmws.api.service;

import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.entity.User;

public interface IUserService extends IEntityService<User> {

    @Nullable User getUserByLoginAndPassword(@Nullable final String login, @Nullable final String password);
    @Nullable User setNewPassword(@Nullable final User user, @Nullable final String password);
    @Nullable User findById(@Nullable final String id);
    void changeUserRole(@Nullable final User user, @Nullable final String role);

}