package ru.levin.tmws.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import ru.levin.tmws.dto.UserDTO;

import javax.persistence.QueryHint;

@Repository
public interface IUserRepository extends JpaRepository<UserDTO, String> {

    @QueryHints(value = {@QueryHint(name="org.hibernate.cacheable",value="true")})
    @Nullable UserDTO findByLoginAndPassword(@NotNull final String login, @NotNull final String hash);

}
