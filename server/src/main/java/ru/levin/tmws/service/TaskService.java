package ru.levin.tmws.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.service.ITaskService;
import ru.levin.tmws.entity.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public final class TaskService extends AbstractEntityService<Task> implements ITaskService {

    @NotNull
    final List<Task> list = new ArrayList<>();

    public TaskService(@NotNull final EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    public @NotNull List<Task> getAll() {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        @NotNull final List<Task> tasks = entityManager
                .createQuery("from Task ", Task.class)
                .getResultList();
        entityManager.getTransaction().commit();
        return tasks;
    }

    @Override
    @NotNull
    public List<Task> findAllByUserIdAndProjectId(@Nullable final String userId, @Nullable final String projectId) {
        if (userId == null || projectId == null) return list;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        @NotNull final List<Task> tasks = entityManager
                .createQuery(
                        "from Task t where t.user.id = '" + userId + "' and t.project.id = '" + projectId + "'",
                        Task.class
                )
                .getResultList();
        entityManager.getTransaction().commit();
        return tasks;
    }

    @Override
    @NotNull
    public List<Task> findAllByUserId(@Nullable final String userId) {
        if (userId == null) return list;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        @NotNull final List<Task> tasks = entityManager
                .createQuery("from Task t where t.user.id = '" + userId + "'", Task.class)
                .getResultList();
        entityManager.getTransaction().commit();
        return tasks;
    }

    @Override
    public void removeAllByUserIdAndProjectId(@Nullable final String userId, @Nullable final String projectId) {
        if (userId == null || userId.isEmpty()) return;
        if (projectId == null || projectId.isEmpty()) return;

        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery(
                "delete from Task t where t.user.id = '" + userId + "' and t.project.id = '" + projectId + "'"
        ).executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public void removeByUserId(@Nullable final String userId) {
        if (userId == null) return;

        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Task t where t.user.id = '" + userId + "'").executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    @Nullable
    public Task findOneByIndex(@Nullable final String userId, final int index) {
        if (userId == null) return null;
        if (index < 0) return null;
        return getAll().get(index - 1);
    }

    @Override
    public @NotNull List<Task> findAllByPartOfNameOrDescription(final @Nullable String partOfName) {
        if (partOfName == null) return list;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        @NotNull final List<Task> tasks = entityManager
                .createQuery(
                        "from Task t where t.name like '%" + partOfName + "%' or t.description like '%" + partOfName + "%'",
                        Task.class
                )
                .getResultList();
        entityManager.getTransaction().commit();
        return tasks;
    }

    @Override
    @Nullable
    public Task save(@Nullable final Task entity) {
        if (entity == null) return null;
        if (entity.getName() == null || entity.getName().isEmpty()) return null;

        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    @Nullable
    public Task update(@Nullable final Task entity) {
        if (entity == null) return null;
        if (entity.getId() == null || entity.getId().isEmpty()) return null;
        if (entity.getName() == null || entity.getName().isEmpty()) return null;

        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public boolean remove(final @Nullable Task entity) {
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
        entityManager.createQuery("delete from Task").executeUpdate();
        entityManager.getTransaction().commit();
        return true;
    }

    @Nullable
    @Override
    public Task findOneById(final @Nullable String id) {
        if (id == null) return null;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        @Nullable final Task task = entityManager.find(Task.class, id);
        entityManager.getTransaction().commit();
        return task;
    }

}
