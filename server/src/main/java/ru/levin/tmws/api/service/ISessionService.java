package ru.levin.tmws.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.dto.SessionDTO;

import java.util.List;

public interface ISessionService extends IEntityService<SessionDTO> {

    void removeByUserId(@Nullable final String userId);
    @NotNull List<SessionDTO> findAllByUserId(@Nullable final String userId);
    @Nullable SessionDTO findById(@Nullable final String id);

}
