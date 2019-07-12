package ru.levin.tmws.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.repository.ITaskRepository;
import ru.levin.tmws.dto.TaskDTO;
import ru.levin.tmws.entity.Task;

import javax.persistence.EntityManager;
import java.util.List;

public final class TaskRepository extends AbstractRepository<TaskDTO> implements ITaskRepository {

    public TaskRepository(final @NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @NotNull
    public List<TaskDTO> findAllByUserIdProjectId(@NotNull final String userId, @NotNull final String projectId) {
        @NotNull final List<TaskDTO> tasks = entityManager
                .createQuery("from TaskDTO t where t.userId = '" + userId + "' and t.projectId = '" + projectId + "'", TaskDTO.class)
                .getResultList();
        return tasks;
    }

    @Override
    @NotNull
    public List<TaskDTO> findAllByUserId(@NotNull final String userId) {
        final List<TaskDTO> tasks = entityManager
                .createQuery("from TaskDTO t where t.userId = '" + userId + "'", TaskDTO.class).getResultList();
        return tasks;
    }

    @Override
    public void removeByUserId(@NotNull final String userId) {
        entityManager.createQuery("delete from Task t where t.user.id = '" + userId + "'").executeUpdate();
    }

    @Override
    public @NotNull List<TaskDTO> findAllByPartOfNameOrDescription(final @NotNull String name) {
        @NotNull final List<TaskDTO> tasks = entityManager
                .createQuery("from TaskDTO t where t.name like '%" + name + "%' or t.description like '%" + name + "%'", TaskDTO.class)
                .getResultList();
        return tasks;
    }

    @Override
    public @NotNull List<TaskDTO> findAll() {
        return entityManager.createQuery("from TaskDTO", TaskDTO.class).getResultList();
    }

    @Nullable
    @Override
    public TaskDTO findOne(final @NotNull String id) {
        return entityManager.find(TaskDTO.class, id);
    }

    @Nullable
    @Override
    public void persist(final @NotNull TaskDTO entity) {
        entityManager.persist(entity);
    }

    @Override
    public void merge(final @NotNull TaskDTO entity) {
        entityManager.merge(entity);
    }

    @Override
    public void remove(final @NotNull TaskDTO entity) {
        @Nullable final Task task = entityManager.find(Task.class, entity.getId());
        if (task == null) return;
        entityManager.remove(task);
    }

    @Override
    public void removeAll() {
        entityManager.createQuery("delete from Task").executeUpdate();
    }

}
