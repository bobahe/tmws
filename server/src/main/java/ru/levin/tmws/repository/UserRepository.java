package ru.levin.tmws.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.repository.IUserRepository;
import ru.levin.tmws.entity.User;

public final class UserRepository extends AbstractRepository<User> implements IUserRepository {

    @Nullable
    public User findOneByLoginAndPassword(@NotNull final String login, @NotNull final String password) {
        return storageMap.values().stream()
                .filter(user -> login.equals(user.getLogin()) && password.equals(user.getPassword()))
                .findFirst().orElse(null);
    }

    @Override
    public @Nullable User findById(final @Nullable String id) {
        return storageMap.get(id);
    }

}
