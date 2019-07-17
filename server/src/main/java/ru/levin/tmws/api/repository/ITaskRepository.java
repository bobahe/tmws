package ru.levin.tmws.api.repository;

import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;
import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.dto.TaskDTO;

import java.util.List;

@Repository
public interface ITaskRepository extends FullEntityRepository<TaskDTO, String> {

    @NotNull List<TaskDTO> findByUserIdAndProjectId(@NotNull final String userId, @NotNull final String projectId);
    @NotNull List<TaskDTO> findByUserId(@NotNull final String userId);
    void removeByUserId(@NotNull final String userId);

    @NotNull
    List<TaskDTO> findByNameLikeOrDescriptionLike(@NotNull final String name, @NotNull final String description);

}
