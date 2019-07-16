package ru.levin.tmws.service;

import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.repository.ISessionRepository;
import ru.levin.tmws.api.service.ISessionService;
import ru.levin.tmws.dto.SessionDTO;
import ru.levin.tmws.repository.SessionRepository;
import ru.levin.tmws.util.ServiceUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class SessionService extends AbstractEntityService<SessionDTO> implements ISessionService {

    @NotNull private final List<SessionDTO> list = new ArrayList<>();

    @NotNull
    @Inject
    private EntityManager entityManager;

    @Override
    public @NotNull List<SessionDTO> getAll() {
        @NotNull final ISessionRepository repository = new SessionRepository(entityManager);
        @NotNull final List<SessionDTO> result = repository.findAll();
        return result;
    }

    @Override
    @NotNull
    public List<SessionDTO> findAllByUserId(@Nullable final String userId) {
        if (userId == null) return list;
        @NotNull final ISessionRepository repository = new SessionRepository(entityManager);
        @NotNull final List<SessionDTO> result = repository.findAllByUserId(userId);
        return result;
    }

    @Override
    public @Nullable SessionDTO findById(@Nullable final String id) {
        if (id == null) return null;
        return findOneById(id);
    }

    @Override
    public void removeByUserId(@Nullable final String userId) {
        if (userId == null) return;
        @NotNull final ISessionRepository repository = new SessionRepository(entityManager);
        repository.removeByUserId(userId);
    }

    @Override
    @Nullable
    public SessionDTO save(@Nullable final SessionDTO entity) {
        if (entity == null) return null;
        entity.setSignature(ServiceUtil.sign(entity, "123", 5));
        @NotNull final ISessionRepository repository = new SessionRepository(entityManager);
        repository.persist(entity);
        return entity;
    }

    @Override
    @Nullable
    public SessionDTO update(@Nullable final SessionDTO entity) {
        if (entity == null) return null;
        if (entity.getId() == null || entity.getId().isEmpty()) return null;
        @NotNull final ISessionRepository repository = new SessionRepository(entityManager);
        repository.merge(entity);
        return entity;
    }

    @Override
    public boolean remove(final @Nullable SessionDTO entity) {
        if (entity == null) return false;
        @NotNull final ISessionRepository repository = new SessionRepository(entityManager);
        repository.remove(entity);
        return true;
    }

    @Override
    public boolean removeAll() {
        @NotNull final ISessionRepository repository = new SessionRepository(entityManager);
        repository.removeAll();
        return true;
    }

    @Nullable
    @Override
    public SessionDTO findOneById(final @Nullable String id) {
        if (id == null) return null;
        @NotNull final ISessionRepository repository = new SessionRepository(entityManager);
        @Nullable final SessionDTO session = repository.findOne(id);
        return session;
    }

}
