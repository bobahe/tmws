package ru.levin.tmws.server.repository;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.server.api.repository.ITaskRepository;
import ru.levin.tmws.server.entity.Task;

import java.util.List;
import java.util.stream.Collectors;

public final class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    @NotNull
    public List<Task> findAllByUserIdProjectId(@NotNull final String userId, @NotNull final String projectId) {
        return storageMap.values().stream()
                .filter(task -> userId.equals(task.getUserId()) && projectId.equals(task.getProjectId()))
                .collect(Collectors.toList());
    }

    @Override
    @NotNull
    public List<Task> findAllByUserId(@NotNull final String userId) {
        return storageMap.values().stream()
                .filter(task -> userId.equals(task.getUserId()))
                .collect(Collectors.toList());
    }

    @Override
    public void removeByUserId(@NotNull final String userId) {
        storageMap.values().forEach(task -> {
            if (userId.equals(task.getUserId())) {
                remove(task);
            }
        });
    }

    @Override
    public @NotNull List<Task> findAllByPartOfNameOrDescription(final @NotNull String name) {
        return storageMap.values().stream()
                .filter(task -> {
                    if (task.getName() == null || task.getDescription() == null) return false;
                    return task.getName().contains(name) || task.getDescription().contains(name);
                }).collect(Collectors.toList());
    }

}
