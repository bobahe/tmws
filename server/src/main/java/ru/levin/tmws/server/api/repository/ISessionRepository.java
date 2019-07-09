package ru.levin.tmws.server.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.server.entity.Session;
import ru.levin.tmws.server.entity.Task;

import java.util.List;

public interface ISessionRepository extends IRepository<Session> {

    @NotNull List<Session> findAllByUserId(@NotNull final String userId);
    void removeByUserId(@NotNull final String userId);
    @Nullable Session findById(@Nullable final String id);

}
