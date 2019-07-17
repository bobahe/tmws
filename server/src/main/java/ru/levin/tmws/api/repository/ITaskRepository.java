package ru.levin.tmws.api.repository;

import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.dto.TaskDTO;

import javax.persistence.Cacheable;
import javax.persistence.QueryHint;
import java.util.List;

@Repository
@Cacheable
public interface ITaskRepository extends FullEntityRepository<TaskDTO, String> {

    @Query(hints = {@QueryHint(name="org.hibernate.cacheable",value="true")})
    @NotNull List<TaskDTO> findByUserIdAndProjectId(@NotNull final String userId, @NotNull final String projectId);

    @Query(hints = {@QueryHint(name="org.hibernate.cacheable",value="true")})
    @NotNull List<TaskDTO> findByUserId(@NotNull final String userId);

    void removeByUserId(@NotNull final String userId);

    @NotNull
    @Query(hints = {@QueryHint(name="org.hibernate.cacheable",value="true")})
    List<TaskDTO> findByNameLikeOrDescriptionLike(@NotNull final String name, @NotNull final String description);

}
