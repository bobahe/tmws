package ru.levin.tmws.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.levin.tmws.api.repository.ITaskRepository;
import ru.levin.tmws.api.service.ITaskService;
import ru.levin.tmws.dto.TaskDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService extends AbstractEntityService<TaskDTO> implements ITaskService {

    @NotNull
    final List<TaskDTO> list = new ArrayList<>();

    @NotNull
    private ITaskRepository repository;
    @Autowired
    public void setRepository(@NotNull final ITaskRepository repository) { this.repository = repository; }

    @Override
    public @NotNull List<TaskDTO> getAll() {
        @NotNull final List<TaskDTO> result = repository.findAll();
        return result;
    }

    @Override
    @NotNull
    public List<TaskDTO> findAllByUserIdAndProjectId(@Nullable final String userId, @Nullable final String projectId) {
        if (userId == null || projectId == null) return list;
        @NotNull final List<TaskDTO> tasks = repository.findByUserIdAndProjectId(userId, projectId);
        return tasks;
    }

    @Override
    @NotNull
    public List<TaskDTO> findAllByUserId(@Nullable final String userId) {
        if (userId == null) return list;
        @NotNull final List<TaskDTO> tasks = repository.findByUserId(userId);
        return tasks;
    }

    @Override
    @Transactional
    public void removeByUserId(@Nullable final String userId) {
        if (userId == null) return;
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
        @NotNull String matcher = "%" + partOfName + "%";
        @NotNull final List<TaskDTO> tasks = repository.findByNameLikeOrDescriptionLike(matcher, matcher);
        return tasks;
    }

    @Override
    @Nullable
    @Transactional
    public TaskDTO save(@Nullable final TaskDTO entity) {
        if (entity == null) return null;
        if (entity.getName() == null || entity.getName().isEmpty()) return null;
        repository.save(entity);
        return entity;
    }

    @Override
    @Nullable
    @Transactional
    public TaskDTO update(@Nullable final TaskDTO entity) {
        if (entity == null) return null;
        if (entity.getId() == null || entity.getId().isEmpty()) return null;
        if (entity.getName() == null || entity.getName().isEmpty()) return null;
        repository.save(entity);
        return entity;
    }

    @Override
    @Transactional
    public boolean remove(final @Nullable TaskDTO entity) {
        if (entity == null) return false;
        repository.delete(entity);
        return true;
    }

    @Override
    @Transactional
    public boolean removeAll() {
        repository.findAll().forEach(repository::delete);
        return true;
    }

    @Nullable
    @Override
    public TaskDTO findOneById(final @Nullable String id) {
        if (id == null) return null;
        @Nullable final TaskDTO task = repository.findById(id).orElse(null);
        return task;
    }

}
