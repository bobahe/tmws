package ru.levin.tmws.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.repository.ISessionRepository;
import ru.levin.tmws.api.service.ISessionService;
import ru.levin.tmws.entity.Session;
import ru.levin.tmws.util.ServiceUtil;

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
        return repository.findOne(id);
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
        @Nullable final Session session = repository.persist(entity);
        if (session == null) return null;
        session.setSignature(ServiceUtil.sign(session, "123", 5));
        repository.merge(session);
        return session;
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
