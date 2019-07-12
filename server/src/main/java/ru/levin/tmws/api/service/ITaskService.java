package ru.levin.tmws.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.dto.TaskDTO;

import java.util.List;

public interface ITaskService extends IEntityService<TaskDTO> {

    @NotNull List<TaskDTO> findAllByUserIdAndProjectId(@Nullable final String userId, @Nullable final String projectId);
    @NotNull List<TaskDTO> findAllByUserId(@Nullable final String userId);
    void removeByUserId(@Nullable final String userId);
    @Nullable TaskDTO findOneByIndex(@Nullable final String userId, final int index);
    @NotNull List<TaskDTO> findAllByPartOfNameOrDescription(@Nullable final String partOfName);

}
