package ru.levin.tmws.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.repository.ISessionRepository;
import ru.levin.tmws.entity.Session;

import java.util.List;
import java.util.stream.Collectors;

public final class SessionRepository extends AbstractRepository<Session> implements ISessionRepository {

    @Override
    @NotNull
    public List<Session> findAllByUserId(@NotNull final String userId) {
        return storageMap.values().stream()
                .filter(session -> userId.equals(session.getUserId()))
                .collect(Collectors.toList());
    }

    @Override
    public void removeByUserId(@NotNull final String userId) {
        storageMap.values().forEach(session -> {
            if (userId.equals(session.getUserId())) {
                remove(session);
            }
        });
    }

    @Override
    public @Nullable Session findById(@Nullable final String id) {
        return storageMap.get(id);
    }

}
