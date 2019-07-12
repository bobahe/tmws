package ru.levin.tmws.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.repository.ISessionRepository;
import ru.levin.tmws.api.service.ISessionService;
import ru.levin.tmws.dto.SessionDTO;
import ru.levin.tmws.repository.SessionRepository;
import ru.levin.tmws.util.ServiceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public final class SessionService extends AbstractEntityService<SessionDTO> implements ISessionService {

    @NotNull private final List<SessionDTO> list = new ArrayList<>();

    public SessionService(@NotNull final EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    public @NotNull List<SessionDTO> getAll() {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final ISessionRepository repository = new SessionRepository(entityManager);
        entityManager.getTransaction().begin();
        @NotNull final List<SessionDTO> result = repository.findAll();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    @Override
    @NotNull
    public List<SessionDTO> findAllByUserId(@Nullable final String userId) {
        if (userId == null) return list;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final ISessionRepository repository = new SessionRepository(entityManager);
        entityManager.getTransaction().begin();
        @NotNull final List<SessionDTO> result = repository.findAllByUserId(userId);
        entityManager.getTransaction().commit();
        entityManager.close();
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
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final ISessionRepository repository = new SessionRepository(entityManager);
        entityManager.getTransaction().begin();
        repository.removeByUserId(userId);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    @Nullable
    public SessionDTO save(@Nullable final SessionDTO entity) {
        if (entity == null) return null;
        entity.setSignature(ServiceUtil.sign(entity, "123", 5));
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final ISessionRepository repository = new SessionRepository(entityManager);
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return entity;
    }

    @Override
    @Nullable
    public SessionDTO update(@Nullable final SessionDTO entity) {
        if (entity == null) return null;
        if (entity.getId() == null || entity.getId().isEmpty()) return null;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final ISessionRepository repository = new SessionRepository(entityManager);
        entityManager.getTransaction().begin();
        repository.merge(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return entity;
    }

    @Override
    public boolean remove(final @Nullable SessionDTO entity) {
        if (entity == null) return false;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final ISessionRepository repository = new SessionRepository(entityManager);
        entityManager.getTransaction().begin();
        repository.remove(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public boolean removeAll() {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final ISessionRepository repository = new SessionRepository(entityManager);
        entityManager.getTransaction().begin();
        repository.removeAll();
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Nullable
    @Override
    public SessionDTO findOneById(final @Nullable String id) {
        if (id == null) return null;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final ISessionRepository repository = new SessionRepository(entityManager);
        entityManager.getTransaction().begin();
        @Nullable final SessionDTO session = repository.findOne(id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return session;
    }

}
