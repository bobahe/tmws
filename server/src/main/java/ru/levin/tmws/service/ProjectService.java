package ru.levin.tmws.service;

import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.repository.IProjectRepository;
import ru.levin.tmws.api.service.IProjectService;
import ru.levin.tmws.dto.ProjectDTO;
import ru.levin.tmws.repository.ProjectRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class ProjectService extends AbstractEntityService<ProjectDTO> implements IProjectService {

    @NotNull final List<ProjectDTO> list = new ArrayList<>();

    @NotNull
    @Inject
    private EntityManager entityManager;

    @Override
    public @NotNull List<ProjectDTO> getAll() {
        @NotNull final IProjectRepository repository = new ProjectRepository(entityManager);
        @NotNull final List<ProjectDTO> result = repository.findAll();
        return result;
    }

    @Nullable
    @Override
    public ProjectDTO save(final @Nullable ProjectDTO entity) {
        if (entity == null) return null;
        if (entity.getName() == null || entity.getName().isEmpty()) return null;
        @NotNull final IProjectRepository repository = new ProjectRepository(entityManager);
        repository.persist(entity);
        return entity;
    }

    @Nullable
    @Override
    public ProjectDTO update(final @Nullable ProjectDTO entity) {
        if (entity == null) return null;
        if (entity.getId() == null || entity.getId().isEmpty()) return null;
        if (entity.getName() == null || entity.getName().isEmpty()) return null;
        @NotNull final IProjectRepository repository = new ProjectRepository(entityManager);
        repository.merge(entity);
        return entity;
    }

    @Override
    public boolean remove(final @Nullable ProjectDTO entity) {
        if (entity == null) return false;
        if (entity.getId() == null || entity.getId().isEmpty()) return false;
        @NotNull final IProjectRepository repository = new ProjectRepository(entityManager);
        repository.remove(entity);
        return true;
    }

    @Override
    public boolean removeAll() {
        @NotNull final IProjectRepository repository = new ProjectRepository(entityManager);
        repository.removeAll();
        return true;
    }

    @Nullable
    @Override
    public ProjectDTO findOneById(final @Nullable String id) {
        if (id == null) return null;
        @NotNull final IProjectRepository repository = new ProjectRepository(entityManager);
        @Nullable final ProjectDTO project = repository.findOne(id);
        return project;
    }

    @Override
    public void removeByUserId(@Nullable final String userId) {
        if (userId == null) return;
        @NotNull final IProjectRepository repository = new ProjectRepository(entityManager);
        repository.removeByUserId(userId);
    }

    @Override
    @Nullable
    public ProjectDTO findOneByIndex(@Nullable final String userId, int index) throws IndexOutOfBoundsException {
        if (userId == null) return null;
        return findAllByUserId(userId).get(index - 1);
    }

    @Override
    @NotNull
    public List<ProjectDTO> findAllByUserId(@Nullable final String userId) {
        if (userId == null) return list;
        @NotNull final IProjectRepository repository = new ProjectRepository(entityManager);
        @NotNull final List<ProjectDTO> projects = repository.findAllByUserId(userId);
        return projects;
    }

    @Override
    public @NotNull List<ProjectDTO> findAllByPartOfNameOrDescription(final @Nullable String partOfName) {
        if (partOfName == null) return list;
        @NotNull final IProjectRepository repository = new ProjectRepository(entityManager);
        @NotNull final List<ProjectDTO> projects = repository.findAllByPartOfNameOrDescription(partOfName);
        return projects;
    }

}
