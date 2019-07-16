package ru.levin.tmws.service;

import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.repository.ITaskRepository;
import ru.levin.tmws.api.service.ITaskService;
import ru.levin.tmws.dto.TaskDTO;
import ru.levin.tmws.repository.TaskRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class TaskService extends AbstractEntityService<TaskDTO> implements ITaskService {

    @NotNull
    final List<TaskDTO> list = new ArrayList<>();

    @NotNull
    @Inject
    private EntityManager entityManager;

    @Override
    public @NotNull List<TaskDTO> getAll() {
        @NotNull final ITaskRepository repository = new TaskRepository(entityManager);
        @NotNull final List<TaskDTO> result = repository.findAll();
        return result;
    }

    @Override
    @NotNull
    public List<TaskDTO> findAllByUserIdAndProjectId(@Nullable final String userId, @Nullable final String projectId) {
        if (userId == null || projectId == null) return list;
        @NotNull final ITaskRepository repository = new TaskRepository(entityManager);
        @NotNull final List<TaskDTO> tasks = repository.findAllByUserIdProjectId(userId, projectId);
        return tasks;
    }

    @Override
    @NotNull
    public List<TaskDTO> findAllByUserId(@Nullable final String userId) {
        if (userId == null) return list;
        @NotNull final ITaskRepository repository = new TaskRepository(entityManager);
        @NotNull final List<TaskDTO> tasks = repository.findAllByUserId(userId);
        return tasks;
    }

    @Override
    public void removeByUserId(@Nullable final String userId) {
        if (userId == null) return;
        @NotNull final ITaskRepository repository = new TaskRepository(entityManager);
        repository.removeByUserId(userId);
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
        @NotNull final ITaskRepository repository = new TaskRepository(entityManager);
        @NotNull final List<TaskDTO> tasks = repository.findAllByPartOfNameOrDescription(partOfName);
        return tasks;
    }

    @Override
    @Nullable
    public TaskDTO save(@Nullable final TaskDTO entity) {
        if (entity == null) return null;
        if (entity.getName() == null || entity.getName().isEmpty()) return null;
        @NotNull final ITaskRepository repository = new TaskRepository(entityManager);
        repository.persist(entity);
        return entity;
    }

    @Override
    @Nullable
    public TaskDTO update(@Nullable final TaskDTO entity) {
        if (entity == null) return null;
        if (entity.getId() == null || entity.getId().isEmpty()) return null;
        if (entity.getName() == null || entity.getName().isEmpty()) return null;
        @NotNull final ITaskRepository repository = new TaskRepository(entityManager);
        repository.merge(entity);
        return entity;
    }

    @Override
    public boolean remove(final @Nullable TaskDTO entity) {
        if (entity == null) return false;
        @NotNull final ITaskRepository repository = new TaskRepository(entityManager);
        repository.remove(entity);
        return true;
    }

    @Override
    public boolean removeAll() {
        @NotNull final ITaskRepository repository = new TaskRepository(entityManager);
        repository.removeAll();
        return true;
    }

    @Nullable
    @Override
    public TaskDTO findOneById(final @Nullable String id) {
        if (id == null) return null;
        @NotNull final ITaskRepository repository = new TaskRepository(entityManager);
        @Nullable final TaskDTO task = repository.findOne(id);
        return task;
    }

}
