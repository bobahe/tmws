package ru.levin.tmws.api.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import ru.levin.tmws.dto.SessionDTO;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface ISessionRepository extends JpaRepository<SessionDTO, String> {

    @QueryHints(value = {@QueryHint(name="org.hibernate.cacheable",value="true")})
    @NotNull List<SessionDTO> findByUserId(@NotNull final String userId);

    void removeByUserId(@NotNull final String userId);

}
