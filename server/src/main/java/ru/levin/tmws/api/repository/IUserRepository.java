package ru.levin.tmws.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.dto.UserDTO;

public interface IUserRepository extends IRepository<UserDTO> {

    @Nullable UserDTO findOneByLoginAndPassword(@NotNull final String login, @NotNull final String hash);

}
