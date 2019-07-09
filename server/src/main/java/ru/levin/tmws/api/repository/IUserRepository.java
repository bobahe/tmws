package ru.levin.tmws.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.entity.User;

public interface IUserRepository extends IRepository<User> {

    @Nullable User findOneByLoginAndPassword(@NotNull final String login, @NotNull final String hash);

}
