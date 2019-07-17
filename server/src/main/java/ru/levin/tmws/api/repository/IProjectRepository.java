package ru.levin.tmws.api.repository;

import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.dto.ProjectDTO;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface IProjectRepository extends FullEntityRepository<ProjectDTO, String> {

    void removeByUserId(@NotNull final String userId);

    @Query(hints = {@QueryHint(name="org.hibernate.cacheable",value="true")})
    @NotNull List<ProjectDTO> findByUserId(@NotNull final String userId);

    @Query(hints = {@QueryHint(name="org.hibernate.cacheable",value="true")})
    @NotNull List<ProjectDTO> findByNameLikeOrDescriptionLike(@NotNull final String name, @NotNull final String description);

}
