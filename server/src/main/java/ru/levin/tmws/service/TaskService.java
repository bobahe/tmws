package ru.levin.tmws.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.repository.ITaskRepository;
import ru.levin.tmws.api.service.ITaskService;
import ru.levin.tmws.dto.TaskDTO;
import ru.levin.tmws.repository.TaskRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public final class TaskService extends AbstractEntityService<TaskDTO> implements ITaskService {

    @NotNull
    final List<TaskDTO> list = new ArrayList<>();

    public TaskService(@NotNull final EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    public @NotNull List<TaskDTO> getAll() {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository repository = new TaskRepository(entityManager);
        entityManager.getTransaction().begin();
        @NotNull final List<TaskDTO> result = repository.findAll();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    @Override
    @NotNull
    public List<TaskDTO> findAllByUserIdAndProjectId(@Nullable final String userId, @Nullable final String projectId) {
        if (userId == null || projectId == null) return list;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository repository = new TaskRepository(entityManager);
        entityManager.getTransaction().begin();
        @NotNull final List<TaskDTO> tasks = repository.findAllByUserIdProjectId(userId, projectId);
        entityManager.getTransaction().commit();
        entityManager.close();
        return tasks;
    }

    @Override
    @NotNull
    public List<TaskDTO> findAllByUserId(@Nullable final String userId) {
        if (userId == null) return list;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository repository = new TaskRepository(entityManager);
        entityManager.getTransaction().begin();
        @NotNull final List<TaskDTO> tasks = repository.findAllByUserId(userId);
        entityManager.getTransaction().commit();
        entityManager.close();
        return tasks;
    }

    @Override
    public void removeByUserId(@Nullable final String userId) {
        if (userId == null) return;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository repository = new TaskRepository(entityManager);
        entityManager.getTransaction().begin();
        repository.removeByUserId(userId);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    @Nullable
    public TaskDTO findOneByIndex(@Nullable final String userId, final int index) {
        if (userId == null) return null;
        if (index < 0) return null;
        return findAllByUserId(userId).get(index - 1);
    }

    @Override
    public @NotNull List<TaskDTO> findAllByPartOfNameOrDescription(final @Nullable String partOfName) {
        if (partOfName == null) return list;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository repository = new TaskRepository(entityManager);
        entityManager.getTransaction().begin();
        @NotNull final List<TaskDTO> tasks = repository.findAllByPartOfNameOrDescription(partOfName);
        entityManager.getTransaction().commit();
        entityManager.close();
        return tasks;
    }

    @Override
    @Nullable
    public TaskDTO save(@Nullable final TaskDTO entity) {
        if (entity == null) return null;
        if (entity.getName() == null || entity.getName().isEmpty()) return null;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository repository = new TaskRepository(entityManager);
        entityManager.getTransaction().begin();
        repository.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return entity;
    }

    @Override
    @Nullable
    public TaskDTO update(@Nullable final TaskDTO entity) {
        if (entity == null) return null;
        if (entity.getId() == null || entity.getId().isEmpty()) return null;
        if (entity.getName() == null || entity.getName().isEmpty()) return null;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository repository = new TaskRepository(entityManager);
        entityManager.getTransaction().begin();
        repository.merge(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return entity;
    }

    @Override
    public boolean remove(final @Nullable TaskDTO entity) {
        if (entity == null) return false;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository repository = new TaskRepository(entityManager);
        entityManager.getTransaction().begin();
        repository.remove(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public boolean removeAll() {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository repository = new TaskRepository(entityManager);
        entityManager.getTransaction().begin();
        repository.removeAll();
        entityManager.getTransaction().commit();
        return true;
    }

    @Nullable
    @Override
    public TaskDTO findOneById(final @Nullable String id) {
        if (id == null) return null;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository repository = new TaskRepository(entityManager);
        entityManager.getTransaction().begin();
        @Nullable final TaskDTO task = repository.findOne(id);
        entityManager.getTransaction().commit();
        return task;
    }

}
