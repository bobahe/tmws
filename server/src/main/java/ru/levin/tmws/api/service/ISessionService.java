package ru.levin.tmws.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.entity.Session;

import java.util.List;

public interface ISessionService extends IEntityService<Session> {

    void removeByUserId(@Nullable final String userId);
    @NotNull List<Session> findAllByUserId(@Nullable final String userId);
    @Nullable Session findById(@Nullable final String id);

}
