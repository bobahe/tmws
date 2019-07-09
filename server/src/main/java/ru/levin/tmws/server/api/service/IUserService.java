package ru.levin.tmws.server.api.service;

import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.server.entity.User;

public interface IUserService extends IEntityService<User> {

    @Nullable User getUserByLoginAndPassword(@Nullable final String login, @Nullable final String password);
    @Nullable User setNewPassword(@Nullable final User user, @Nullable final String password);
    void setCurrentUser(@Nullable final User user);
    @Nullable User getCurrentUser();
    @Nullable User findById(@Nullable final String id);
    void changeUserRole(@Nullable final User user, @Nullable final String role);

}
