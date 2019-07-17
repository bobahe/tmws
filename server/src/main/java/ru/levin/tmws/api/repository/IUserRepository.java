package ru.levin.tmws.api.repository;

import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.dto.UserDTO;

import javax.persistence.Cacheable;
import javax.persistence.QueryHint;

@Repository
@Cacheable
public interface IUserRepository extends FullEntityRepository<UserDTO, String> {

    @Query(hints = {@QueryHint(name="org.hibernate.cacheable",value="true")})
    @Nullable UserDTO findByLoginAndPassword(@NotNull final String login, @NotNull final String hash);

}
