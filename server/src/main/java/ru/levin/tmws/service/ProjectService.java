package ru.levin.tmws.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.repository.IProjectRepository;
import ru.levin.tmws.api.service.IProjectService;
import ru.levin.tmws.dto.ProjectDTO;
import ru.levin.tmws.exception.InternalServiceException;
import ru.levin.tmws.repository.ProjectRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProjectService extends AbstractEntityService<ProjectDTO> implements IProjectService {

    @NotNull final List<ProjectDTO> list = new ArrayList<>();

    @Nullable
    @Inject
    private EntityManagerFactory entityManagerFactory;

    @Override
    public @NotNull List<ProjectDTO> getAll() {
        if (entityManagerFactory == null) throw new InternalServiceException();
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository repository = new ProjectRepository(entityManager);
        entityManager.getTransaction().begin();
        @NotNull final List<ProjectDTO> result = repository.findAll();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    @Nullable
    @Override
    public ProjectDTO save(final @Nullable ProjectDTO entity) {
        if (entityManagerFactory == null) throw new InternalServiceException();
        if (entity == null) return null;
        if (entity.getName() == null || entity.getName().isEmpty()) return null;

        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository repository = new ProjectRepository(entityManager);
        entityManager.getTransaction().begin();
        repository.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return entity;
    }

    @Nullable
    @Override
    public ProjectDTO update(final @Nullable ProjectDTO entity) {
        if (entityManagerFactory == null) throw new InternalServiceException();
        if (entity == null) return null;
        if (entity.getId() == null || entity.getId().isEmpty()) return null;
        if (entity.getName() == null || entity.getName().isEmpty()) return null;

        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository repository = new ProjectRepository(entityManager);
        entityManager.getTransaction().begin();
        repository.merge(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return entity;
    }

    @Override
    public boolean remove(final @Nullable ProjectDTO entity) {
        if (entityManagerFactory == null) throw new InternalServiceException();
        if (entity == null) return false;
        if (entity.getId() == null || entity.getId().isEmpty()) return false;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository repository = new ProjectRepository(entityManager);
        entityManager.getTransaction().begin();
        repository.remove(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public boolean removeAll() {
        if (entityManagerFactory == null) throw new InternalServiceException();
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository repository = new ProjectRepository(entityManager);
        entityManager.getTransaction().begin();
        repository.removeAll();
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Nullable
    @Override
    public ProjectDTO findOneById(final @Nullable String id) {
        if (entityManagerFactory == null) throw new InternalServiceException();
        if (id == null) return null;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository repository = new ProjectRepository(entityManager);
        entityManager.getTransaction().begin();
        @Nullable final ProjectDTO project = repository.findOne(id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return project;
    }

    @Override
    public void removeByUserId(@Nullable final String userId) {
        if (entityManagerFactory == null) throw new InternalServiceException();
        if (userId == null) return;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository repository = new ProjectRepository(entityManager);
        entityManager.getTransaction().begin();
        repository.removeByUserId(userId);
        entityManager.getTransaction().commit();
        entityManager.close();
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
        if (entityManagerFactory == null) throw new InternalServiceException();
        if (userId == null) return list;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository repository = new ProjectRepository(entityManager);
        entityManager.getTransaction().begin();
        @NotNull final List<ProjectDTO> projects = repository.findAllByUserId(userId);
        entityManager.getTransaction().commit();
        entityManager.close();
        return projects;
    }

    @Override
    public @NotNull List<ProjectDTO> findAllByPartOfNameOrDescription(final @Nullable String partOfName) {
        if (entityManagerFactory == null) throw new InternalServiceException();
        if (partOfName == null) return list;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository repository = new ProjectRepository(entityManager);
        entityManager.getTransaction().begin();
        @NotNull final List<ProjectDTO> projects = repository.findAllByPartOfNameOrDescription(partOfName);
        entityManager.getTransaction().commit();
        entityManager.close();
        return projects;
    }

}
