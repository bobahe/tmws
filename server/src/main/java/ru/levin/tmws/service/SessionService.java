package ru.levin.tmws.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.service.ISessionService;
import ru.levin.tmws.entity.Session;
import ru.levin.tmws.util.ServiceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public final class SessionService extends AbstractEntityService<Session> implements ISessionService {

    @NotNull private final List<Session> list = new ArrayList<>();

    public SessionService(@NotNull final EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    public @NotNull List<Session> getAll() {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        @NotNull final List<Session> sessions = entityManager
                .createQuery("from Session", Session.class)
                .getResultList();
        entityManager.getTransaction().commit();
        return sessions;
    }

    @Override
    @NotNull
    public List<Session> findAllByUserId(@Nullable final String userId) {
        if (userId == null) return list;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        @NotNull final List<Session> sessions = entityManager
                .createQuery("from Session s where s.user.id = '" + userId + "'", Session.class)
                .getResultList();
        entityManager.getTransaction().commit();
        return sessions;
    }

    @Override
    public @Nullable Session findById(@Nullable final String id) {
        if (id == null) return null;
        return findOneById(id);
    }

    @Override
    public void removeByUserId(@Nullable final String userId) {
        if (userId == null) return;

        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Session s where s.user.id = '" + userId + "'").executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    @Nullable
    public Session save(@Nullable final Session entity) {
        if (entity == null) return null;
        entity.setSignature(ServiceUtil.sign(entity, "123", 5));
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    @Nullable
    public Session update(@Nullable final Session entity) {
        if (entity == null) return null;
        if (entity.getId() == null || entity.getId().isEmpty()) return null;

        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public boolean remove(final @Nullable Session entity) {
        if (entity == null) return false;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public boolean removeAll() {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Session").executeUpdate();
        entityManager.getTransaction().commit();
        return true;
    }

    @Nullable
    @Override
    public Session findOneById(final @Nullable String id) {
        if (id == null) return null;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        @Nullable final Session session = entityManager.find(Session.class, id);
        entityManager.getTransaction().commit();
        return session;
    }

}
