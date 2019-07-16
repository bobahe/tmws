package ru.levin.tmws.api.repository;

import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;
import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.dto.SessionDTO;

import java.util.List;

@Repository
public interface ISessionRepository extends FullEntityRepository<SessionDTO, String> {

    void removeAll();
    @NotNull List<SessionDTO> findByUserId(@NotNull final String userId);
    void removeByUserId(@NotNull final String userId);

}
