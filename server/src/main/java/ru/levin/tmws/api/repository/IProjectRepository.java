package ru.levin.tmws.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.dto.ProjectDTO;

import java.util.List;

public interface IProjectRepository extends IRepository<ProjectDTO> {

    void removeByUserId(@NotNull final String userId);
    @NotNull List<ProjectDTO> findAllByUserId(@NotNull final String userId);
    @NotNull List<ProjectDTO> findAllByPartOfNameOrDescription(@NotNull final String name);

}
