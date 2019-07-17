package ru.levin.tmws.api.repository;

import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.dto.SessionDTO;

import javax.persistence.Cacheable;
import javax.persistence.QueryHint;
import java.util.List;

@Repository
@Cacheable
public interface ISessionRepository extends FullEntityRepository<SessionDTO, String> {

    @Query(hints = {@QueryHint(name="org.hibernate.cacheable",value="true")})
    @NotNull List<SessionDTO> findByUserId(@NotNull final String userId);

    void removeByUserId(@NotNull final String userId);

}
