package ru.levin.tmws.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
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
    private final SqlSessionFactory sessionFactory;

    public SessionService(@NotNull final SqlSessionFactory sessionFactory) {
        super(sessionFactory, ISessionRepository.class);
        this.sessionFactory = sessionFactory;
    }

    @Override
    @NotNull
    public List<Session> findAllByUserId(@Nullable final String userId) {
        if (userId == null) return list;
        try (SqlSession session = sessionFactory.openSession()) {
            ISessionRepository repository = session.getMapper(repositoryClass);
            return repository.findAllByUserId(userId);
        }
    }

    @Override
    public @Nullable Session findById(@Nullable final String id) {
        try (SqlSession session = sessionFactory.openSession()) {
            ISessionRepository repository = session.getMapper(repositoryClass);
            return repository.findOne(id);
        }
    }

    @Override
    public void removeByUserId(@Nullable final String userId) {
        if (userId == null) return;

        final SqlSession session = sessionFactory.openSession();
        try {
            ISessionRepository repository = session.getMapper(repositoryClass);
            repository.removeByUserId(userId);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    @Nullable
    public Session save(@Nullable final Session entity) {
        if (entity == null) return null;
        entity.setSignature(ServiceUtil.sign(entity, "123", 5));
        final SqlSession session = sessionFactory.openSession();
        try {
            ISessionRepository repository = session.getMapper(repositoryClass);
            repository.persist(entity);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }

        return entity;
    }

    @Override
    @Nullable
    public Session update(@Nullable final Session entity) {
        if (entity == null) return null;
        if (entity.getId() == null || entity.getId().isEmpty()) return null;

        final SqlSession session = sessionFactory.openSession();
        try {
            ISessionRepository repository = session.getMapper(repositoryClass);
            repository.merge(entity);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return entity;
    }

}
