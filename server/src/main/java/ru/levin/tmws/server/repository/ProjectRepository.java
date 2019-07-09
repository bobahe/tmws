package ru.levin.tmws.server.repository;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.server.api.repository.IProjectRepository;
import ru.levin.tmws.server.entity.Project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public final class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {

    @Override
    public void removeByUserId(@NotNull String userId) {
        @NotNull final List<Project> projectsToRemove = new ArrayList<>();
        storageMap.values().forEach(project -> {
            if (userId.equals(project.getUserId())) projectsToRemove.add(project);
        });
        projectsToRemove.forEach(project -> storageMap.remove(project.getId()));
    }

    @Override
    public @NotNull List<Project> findAllByUserId(@NotNull String userId) {
        return storageMap.values().stream()
                .filter(project -> userId.equals(project.getUserId()))
                .collect(Collectors.toList());
    }

    @Override
    public @NotNull List<Project> findAllByPartOfNameOrDescription(final @NotNull String name) {
        return storageMap.values().stream()
                .filter(project -> {
                    if (project.getName() == null || project.getDescription() == null) return false;
                    return project.getName().contains(name) || project.getDescription().contains(name);
                }).collect(Collectors.toList());
    }

}
