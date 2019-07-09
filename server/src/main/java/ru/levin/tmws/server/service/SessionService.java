package ru.levin.tmws.server.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.server.api.repository.ISessionRepository;
import ru.levin.tmws.server.api.repository.ITaskRepository;
import ru.levin.tmws.server.api.service.ISessionService;
import ru.levin.tmws.server.api.service.ITaskService;
import ru.levin.tmws.server.entity.Session;
import ru.levin.tmws.server.entity.Task;
import ru.levin.tmws.server.util.ServiceUtil;

import java.util.ArrayList;
import java.util.List;

public final class SessionService extends AbstractEntityService<Session, ISessionRepository> implements ISessionService {

    @NotNull private final List<Session> list = new ArrayList<>();

    @NotNull
    private final ISessionRepository repository;

    public SessionService(@NotNull final ISessionRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    @NotNull
    public List<Session> findAllByUserId(@Nullable final String userId) {
        if (userId == null) return list;
        return repository.findAllByUserId(userId);
    }

    @Override
    public @Nullable Session findById(@Nullable final String id) {
        return repository.findById(id);
    }

    @Override
    public void removeByUserId(@Nullable final String userId) {
        if (userId == null) return;
        repository.findAllByUserId(userId);
    }

    @Override
    @Nullable
    public Session save(@Nullable final Session entity) {
        if (entity == null) return null;
        repository.persist(entity);
        entity.setSignature(ServiceUtil.sign(entity, "123", 5));
        repository.merge(entity);
        return entity;
    }

    @Override
    @Nullable
    public Session update(@Nullable final Session entity) {
        if (entity == null) return null;
        if (entity.getId() == null || entity.getId().isEmpty()) return null;
        if (repository.findOne(entity.getId()) == null) return null;

        repository.merge(entity);
        return entity;
    }

}
