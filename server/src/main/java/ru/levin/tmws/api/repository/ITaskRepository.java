package ru.levin.tmws.api.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import ru.levin.tmws.dto.TaskDTO;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface ITaskRepository extends JpaRepository<TaskDTO, String> {

    @QueryHints(value = {@QueryHint(name="org.hibernate.cacheable",value="true")})
    @NotNull List<TaskDTO> findByUserIdAndProjectId(@NotNull final String userId, @NotNull final String projectId);

    @QueryHints(value = {@QueryHint(name="org.hibernate.cacheable",value="true")})
    @NotNull List<TaskDTO> findByUserId(@NotNull final String userId);

    void removeByUserId(@NotNull final String userId);

    @NotNull
    @QueryHints(value = {@QueryHint(name="org.hibernate.cacheable",value="true")})
    List<TaskDTO> findByNameLikeOrDescriptionLike(@NotNull final String name, @NotNull final String description);

}
