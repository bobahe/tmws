package ru.levin.tmws.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.repository.ITaskRepository;
import ru.levin.tmws.api.service.ITaskService;
import ru.levin.tmws.entity.Task;

import java.util.ArrayList;
import java.util.List;

public final class TaskService extends AbstractEntityService<Task, ITaskRepository> implements ITaskService {

    @NotNull
    final List<Task> list = new ArrayList<>();

    @NotNull
    private final SqlSessionFactory sessionFactory;

    public TaskService(@NotNull final SqlSessionFactory sessionFactory) {
        super(sessionFactory, ITaskRepository.class);
        this.sessionFactory = sessionFactory;
    }

    @Override
    @NotNull
    public List<Task> findAllByUserIdAndProjectId(@Nullable final String userId, @Nullable final String projectId) {
        if (userId == null || projectId == null) return list;
        try (final SqlSession session = sessionFactory.openSession()) {
            @NotNull final ITaskRepository repository = session.getMapper(repositoryClass);
            return repository.findAllByUserIdProjectId(userId, projectId);
        }
    }

    @Override
    @NotNull
    public List<Task> findAllByUserId(@Nullable final String userId) {
        if (userId == null) return list;
        try (final SqlSession session = sessionFactory.openSession()) {
            @NotNull final ITaskRepository repository = session.getMapper(repositoryClass);
            return repository.findAllByUserId(userId);
        }
    }

    @Override
    public void removeAllByUserIdAndProjectId(@Nullable final String userId, @Nullable final String projectId) {
        if (userId == null || userId.isEmpty()) return;
        if (projectId == null || projectId.isEmpty()) return;

        @Nullable final SqlSession session = sessionFactory.openSession();
        if (session == null) throw new SqlSessionException();
        try {
            @NotNull final ITaskRepository repository = session.getMapper(repositoryClass);
            repository.findAllByUserIdProjectId(userId, projectId).forEach(repository::remove);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void removeByUserId(@Nullable final String userId) {
        if (userId == null) return;

        @Nullable final SqlSession session = sessionFactory.openSession();
        if (session == null) throw new SqlSessionException();
        try {
            @NotNull final ITaskRepository repository = session.getMapper(repositoryClass);
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
    public Task findOneByIndex(@Nullable final String userId, final int index) {
        if (userId == null) return null;
        if (index < 0) return null;
        try (final SqlSession session = sessionFactory.openSession()) {
            @NotNull final ITaskRepository repository = session.getMapper(repositoryClass);
            return repository.findAllByUserId(userId).get(index - 1);
        }
    }

    @Override
    public @NotNull List<Task> findAllByPartOfNameOrDescription(final @Nullable String partOfName) {
        if (partOfName == null) return list;
        try (final SqlSession session = sessionFactory.openSession()) {
            @NotNull final ITaskRepository repository = session.getMapper(repositoryClass);
            return repository.findAllByPartOfNameOrDescription(partOfName);
        }
    }

    @Override
    @Nullable
    public Task save(@Nullable final Task entity) {
        if (entity == null) return null;
        if (entity.getName() == null || entity.getName().isEmpty()) return null;

        @Nullable final SqlSession session = sessionFactory.openSession();
        if (session == null) throw new SqlSessionException();
        try {
            @NotNull final ITaskRepository repository = session.getMapper(repositoryClass);
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
    public Task update(@Nullable final Task entity) {
        if (entity == null) return null;
        if (entity.getId() == null || entity.getId().isEmpty()) return null;
        if (entity.getName() == null || entity.getName().isEmpty()) return null;

        @Nullable final SqlSession session = sessionFactory.openSession();
        if (session == null) throw new SqlSessionException();
        try {
            @NotNull final ITaskRepository repository = session.getMapper(repositoryClass);
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
