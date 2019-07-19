package ru.levin.tmws.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.levin.tmws.api.repository.IProjectEntityRepository;
import ru.levin.tmws.api.repository.IProjectRepository;
import ru.levin.tmws.api.service.IProjectService;
import ru.levin.tmws.dto.ProjectDTO;
import ru.levin.tmws.entity.Project;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService extends AbstractEntityService<ProjectDTO> implements IProjectService {

    @NotNull final List<ProjectDTO> list = new ArrayList<>();

    @NotNull
    private IProjectRepository repository;
    @Autowired
    public void setRepository(@NotNull final IProjectRepository repository) { this.repository = repository; }

    @NotNull
    private IProjectEntityRepository entityRepository;
    @Autowired
    public void setEntityRepository(@NotNull final IProjectEntityRepository entityRepository) { this.entityRepository = entityRepository; }

    @Override
    public @NotNull List<ProjectDTO> getAll() {
        final Iterable<ProjectDTO> all = repository.findAll();
        @NotNull final List<ProjectDTO> result = repository.findAll();
        return result;
    }

    @Nullable
    @Override
    @Transactional
    public ProjectDTO save(final @Nullable ProjectDTO entity) {
        if (entity == null) return null;
        if (entity.getName() == null || entity.getName().isEmpty()) return null;
        repository.save(entity);
        return entity;
    }

    @Nullable
    @Override
    @Transactional
    public ProjectDTO update(final @Nullable ProjectDTO entity) {
        if (entity == null) return null;
        if (entity.getId() == null || entity.getId().isEmpty()) return null;
        if (entity.getName() == null || entity.getName().isEmpty()) return null;
        repository.save(entity);
        return entity;
    }

    @Override
    @Transactional
    public boolean remove(final @Nullable ProjectDTO entity) {
        if (entity == null) return false;
        if (entity.getId() == null || entity.getId().isEmpty()) return false;
        @Nullable final Project savedEntity = entityRepository.findById(entity.getId()).orElse(null);
        if (savedEntity == null) return false;
        entityRepository.delete(savedEntity);
        return true;
    }

    @Override
    @Transactional
    public boolean removeAll() {
        entityRepository.findAll().forEach(entityRepository::delete);
        return true;
    }

    @Nullable
    @Override
    public ProjectDTO findOneById(final @Nullable String id) {
        if (id == null) return null;
        @Nullable final ProjectDTO project = repository.findById(id).orElse(null);
        return project;
    }

    @Override
    @Transactional
    public void removeByUserId(@Nullable final String userId) {
        if (userId == null) return;
        entityRepository.findByUser_id(userId).forEach(entityRepository::delete);
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
        @NotNull final List<ProjectDTO> projects = repository.findByUserId(userId);
        return projects;
    }

    @Override
    public @NotNull List<ProjectDTO> findAllByPartOfNameOrDescription(final @Nullable String partOfName) {
        if (partOfName == null) return list;
        @NotNull String matcher = "%" + partOfName + "%";
        @NotNull final List<ProjectDTO> projects = repository.findByNameLikeOrDescriptionLike(matcher, matcher);
        return projects;
    }

}
