package ru.levin.tmws.server.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.server.entity.Project;

import java.util.List;

public interface IProjectRepository extends IRepository<Project> {

    void removeByUserId(@NotNull final String userId);
    @NotNull List<Project> findAllByUserId(@NotNull final String userId);
    @NotNull List<Project> findAllByPartOfNameOrDescription(@NotNull final String name);

}
