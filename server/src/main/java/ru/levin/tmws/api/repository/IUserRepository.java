package ru.levin.tmws.api.repository;

import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.dto.UserDTO;

@Repository
public interface IUserRepository extends FullEntityRepository<UserDTO, String> {

    @Nullable UserDTO findByLoginAndPassword(@NotNull final String login, @NotNull final String hash);

}
