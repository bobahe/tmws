package ru.levin.tmws.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.repository.IProjectRepository;
import ru.levin.tmws.api.service.IProjectService;
import ru.levin.tmws.entity.Project;

import java.util.ArrayList;
import java.util.List;

public final class ProjectService extends AbstractEntityService<Project, IProjectRepository> implements IProjectService {

    @NotNull final List<Project> list = new ArrayList<>();

    public ProjectService(@NotNull final SqlSessionFactory sessionFactory) {
        super(sessionFactory, IProjectRepository.class);
    }

    @Nullable
    @Override
    public Project save(final @Nullable Project entity) {
        if (entity == null) return null;

        @Nullable final SqlSession session = sessionFactory.openSession();
        if (session == null) throw new SqlSessionException();
        try {
            @NotNull final IProjectRepository repository = session.getMapper(repositoryClass);
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

    @Nullable
    @Override
    public Project update(final @Nullable Project entity) {
        if (entity == null) return null;

        @Nullable final SqlSession session = sessionFactory.openSession();
        if (session == null) throw new SqlSessionException();
        try {
            @NotNull final IProjectRepository repository = session.getMapper(repositoryClass);
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

    @Override
    public void removeByUserId(@Nullable final String userId) {
        if (userId == null) return;

        @Nullable final SqlSession session = sessionFactory.openSession();
        if (session == null) throw new SqlSessionException();
        try {
            @NotNull final IProjectRepository repository = session.getMapper(repositoryClass);
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
    public Project findOneByIndex(@Nullable final String userId, int index) throws IndexOutOfBoundsException {
        if (userId == null) return null;
        try (final SqlSession session = sessionFactory.openSession()) {
            @NotNull final IProjectRepository repository = session.getMapper(repositoryClass);
            return repository.findAllByUserId(userId).get(index - 1);
        }
    }

    @Override
    @NotNull
    public List<Project> findAllByUserId(@Nullable final String userId) {
        if (userId == null) return list;
        try (final SqlSession session = sessionFactory.openSession()) {
            @NotNull final IProjectRepository repository = session.getMapper(repositoryClass);
            return repository.findAllByUserId(userId);
        }
    }

    @Override
    public @NotNull List<Project> findAllByPartOfNameOrDescription(final @Nullable String partOfName) {
        if (partOfName == null) return list;
        try (final SqlSession session = sessionFactory.openSession()) {
            @NotNull final IProjectRepository repository = session.getMapper(repositoryClass);
            return repository.findAllByPartOfNameOrDescription(partOfName);
        }
    }

}
