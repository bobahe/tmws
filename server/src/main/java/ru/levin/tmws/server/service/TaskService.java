package ru.levin.tmws.server.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.server.api.repository.ITaskRepository;
import ru.levin.tmws.server.api.service.ITaskService;
import ru.levin.tmws.server.entity.Task;

import java.util.ArrayList;
import java.util.List;

public final class TaskService extends AbstractEntityService<Task, ITaskRepository> implements ITaskService {

    @NotNull final List<Task> list = new ArrayList<>();

    @NotNull
    private final ITaskRepository repository;

    public TaskService(@NotNull final ITaskRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    @NotNull
    public List<Task> findAllByUserIdAndProjectId(@Nullable final String userId, @Nullable final String projectId) {
        if (userId == null || projectId == null) return list;
        return repository.findAllByUserIdProjectId(userId, projectId);
    }

    @Override
    @NotNull
    public List<Task> findAllByUserId(@Nullable final String userId) {
        if (userId == null) return list;
        return repository.findAllByUserId(userId);
    }

    @Override
    public void removeAllByUserIdAndProjectId(@Nullable final String userId, @Nullable final String projectId) {
        if (userId == null || userId.isEmpty()) return;
        if (projectId == null || projectId.isEmpty()) return;

        findAllByUserIdAndProjectId(userId, projectId).forEach(repository::remove);
    }

    @Override
    public void removeByUserId(@Nullable final String userId) {
        if (userId == null) return;
        repository.findAllByUserId(userId);
    }

    @Override
    @Nullable
    public Task findOneByIndex(@Nullable final String userId, final int index) {
        if  (userId == null) return null;
        if (index < 0) return null;
        return repository.findAllByUserId(userId).get(index - 1);
    }

    @Override
    public @NotNull List<Task> findAllByPartOfNameOrDescription(final @Nullable String partOfName) {
        if (partOfName == null) return list;
        return repository.findAllByPartOfNameOrDescription(partOfName);
    }

    @Override
    @Nullable
    public Task save(@Nullable final Task entity) {
        if (entity == null) return null;
        if (entity.getName() == null || entity.getName().isEmpty()) return null;

        repository.persist(entity);
        return entity;
    }

    @Override
    @Nullable
    public Task update(@Nullable final Task entity) {
        if (entity == null) return null;
        if (entity.getId() == null || entity.getId().isEmpty()) return null;
        if (entity.getName() == null || entity.getName().isEmpty()) return null;

        if (repository.findOne(entity.getId()) == null) {
            throw new IllegalStateException("Can not update task. There is no such task in storage.");
        }

        repository.merge(entity);
        return entity;
    }

}
