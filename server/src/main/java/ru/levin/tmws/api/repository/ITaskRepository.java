package ru.levin.tmws.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.dto.TaskDTO;

import java.util.List;

public interface ITaskRepository extends IRepository<TaskDTO> {

    @NotNull List<TaskDTO> findAllByUserIdProjectId(@NotNull final String userId, @NotNull final String projectId);
    @NotNull List<TaskDTO> findAllByUserId(@NotNull final String userId);
    void removeByUserId(@NotNull final String userId);
    @NotNull List<TaskDTO> findAllByPartOfNameOrDescription(@NotNull final String name);

}
