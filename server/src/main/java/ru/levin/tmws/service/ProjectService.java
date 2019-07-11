package ru.levin.tmws.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.service.IProjectService;
import ru.levin.tmws.entity.Project;
import ru.levin.tmws.entity.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public final class ProjectService extends AbstractEntityService<Project> implements IProjectService {

    @NotNull final List<Project> list = new ArrayList<>();

    public ProjectService(@NotNull final EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    public @NotNull List<Project> getAll() {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        @NotNull final List<Project> projects = entityManager
                .createQuery("from Project", Project.class)
                .getResultList();
        entityManager.getTransaction().commit();
        return projects;
    }

    @Nullable
    @Override
    public Project save(final @Nullable Project entity) {
        if (entity == null) return null;
        if (entity.getName() == null || entity.getName().isEmpty()) return null;

        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Nullable
    @Override
    public Project update(final @Nullable Project entity) {
        if (entity == null) return null;
        if (entity.getId() == null || entity.getId().isEmpty()) return null;
        if (entity.getName() == null || entity.getName().isEmpty()) return null;

        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public boolean remove(final @Nullable Project entity) {
        if (entity == null) return false;

        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public boolean removeAll() {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        @NotNull final List<Task> tasks = entityManager
                .createQuery("from Task", Task.class).getResultList();
        tasks.forEach(entityManager::remove);
        entityManager.createQuery("delete from Project", Project.class);
        entityManager.getTransaction().commit();
        return true;
    }

    @Nullable
    @Override
    public Project findOneById(final @Nullable String id) {
        if (id == null) return null;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        @Nullable final Project project = entityManager.find(Project.class, id);
        entityManager.getTransaction().commit();
        return project;
    }

    @Override
    public void removeByUserId(@Nullable final String userId) {
        if (userId == null) return;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        @NotNull final List<Project> projects = entityManager
                .createQuery("from Project p where p.user.id = '" + userId + "'", Project.class).getResultList();
        projects.forEach(project ->
                entityManager
                        .createQuery("delete from Task t where t.project.id = '" + project.getId() + "'", Task.class)
        );
        entityManager.createQuery("delete from Project p where p.user.id = '" + userId + "'", Project.class);
        entityManager.getTransaction().commit();
    }

    @Override
    @Nullable
    public Project findOneByIndex(@Nullable final String userId, int index) throws IndexOutOfBoundsException {
        if (userId == null) return null;
        return getAll().get(index - 1);
    }

    @Override
    @NotNull
    public List<Project> findAllByUserId(@Nullable final String userId) {
        if (userId == null) return list;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        @NotNull final List<Project> projects = entityManager
                .createQuery("from Project p where p.user.id = '" + userId + "'", Project.class).getResultList();
        entityManager.getTransaction().commit();
        return projects;
    }

    @Override
    public @NotNull List<Project> findAllByPartOfNameOrDescription(final @Nullable String partOfName) {
        if (partOfName == null) return list;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        @NotNull final List<Project> projects = entityManager
                .createQuery(
                        "from Project p where p.name like %" + partOfName + "% or p.description like %" + partOfName + "%",
                        Project.class
                )
                .getResultList();
        entityManager.getTransaction().commit();
        return projects;
    }

}
