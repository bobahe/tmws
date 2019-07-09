package ru.levin.tmws.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.repository.IProjectRepository;
import ru.levin.tmws.api.service.IProjectService;
import ru.levin.tmws.entity.Project;
import ru.levin.tmws.entity.Status;

import java.util.ArrayList;
import java.util.List;

public final class ProjectService extends AbstractEntityService<Project, IProjectRepository> implements IProjectService {

    @NotNull final List<Project> list = new ArrayList<>();

    public ProjectService(@NotNull final IProjectRepository repository) {
        super(repository);
    }

    @Override
    @Nullable
    public Project save(@Nullable final Project entity) {
        if (entity == null) return null;
        if (entity.getName() == null || entity.getName().isEmpty()) return null;
        if (entity.getStatus() == null) entity.setStatus(Status.PLANNED);

        return repository.persist(entity);
    }

    @Override
    @Nullable
    public Project update(@Nullable final Project entity) {
        if (entity == null) return null;
        if (entity.getId() == null || entity.getId().isEmpty()) return null;
        if (entity.getName() == null || entity.getName().isEmpty()) return null;

        if (repository.findOne(entity.getId()) == null) {
            throw new IllegalStateException("Can not update project. There is no such project in storage.");
        }

        repository.merge(entity);
        return entity;
    }

    @Override
    public void removeByUserId(@Nullable final String userId) {
        if (userId == null) return;
        repository.removeByUserId(userId);
    }

    @Override
    @Nullable
    public Project findOneByIndex(@Nullable final String userId, int index) throws IndexOutOfBoundsException {
        if (userId == null) return null;
        return repository.findAllByUserId(userId).get(index - 1);
    }

    @Override
    @NotNull
    public List<Project> findAllByUserId(@Nullable final String userId) {
        if (userId == null) return list;
        return repository.findAllByUserId(userId);
    }

    @Override
    public @NotNull List<Project> findAllByPartOfNameOrDescription(final @Nullable String partOfName) {
        if (partOfName == null) return list;
        return repository.findAllByPartOfNameOrDescription(partOfName);
    }

}
