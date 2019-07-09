package ru.levin.tmws.server.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.server.entity.User;

public interface IUserRepository extends IRepository<User> {

    @Nullable User findOneByLoginAndPassword(@NotNull final String login, @NotNull final String hash);
    @Nullable User findById(@Nullable final String id);

}
