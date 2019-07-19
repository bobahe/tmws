package ru.levin.tmws.api.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import ru.levin.tmws.dto.ProjectDTO;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface IProjectRepository extends JpaRepository<ProjectDTO, String> {

    void removeByUserId(@NotNull final String userId);

    @QueryHints(value = {@QueryHint(name="org.hibernate.cacheable",value="true")})
    @NotNull List<ProjectDTO> findByUserId(@NotNull final String userId);

    @QueryHints(value = {@QueryHint(name="org.hibernate.cacheable",value="true")})
    @NotNull List<ProjectDTO> findByNameLikeOrDescriptionLike(@NotNull final String name, @NotNull final String description);

}
