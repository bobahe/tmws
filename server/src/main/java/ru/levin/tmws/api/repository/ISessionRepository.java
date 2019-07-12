package ru.levin.tmws.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.dto.SessionDTO;

import java.util.List;

public interface ISessionRepository extends IRepository<SessionDTO> {

    @NotNull List<SessionDTO> findAllByUserId(@NotNull final String userId);
    void removeByUserId(@NotNull final String userId);

}
