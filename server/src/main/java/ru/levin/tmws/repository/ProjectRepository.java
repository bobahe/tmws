package ru.levin.tmws.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.repository.IProjectRepository;
import ru.levin.tmws.dto.ProjectDTO;
import ru.levin.tmws.entity.Project;

import javax.persistence.EntityManager;
import java.util.List;

public final class ProjectRepository extends AbstractRepository<ProjectDTO> implements IProjectRepository {

    public ProjectRepository(final @NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void removeByUserId(@NotNull String userId) {
        entityManager.createQuery("delete from Project p where p.user.id = '" + userId + "'").executeUpdate();
    }

    @Override
    public @NotNull List<ProjectDTO> findAllByUserId(@NotNull String userId) {
        @NotNull final List<ProjectDTO> projects = entityManager
                .createQuery("from ProjectDTO p where p.userId = '" + userId + "'", ProjectDTO.class)
                .getResultList();
        return projects;
    }

    @Override
    public @NotNull List<ProjectDTO> findAllByPartOfNameOrDescription(final @NotNull String name) {
        @NotNull final List<ProjectDTO> projects = entityManager
                .createQuery(
                        "from ProjectDTO p where p.name like '%" + name + "%' or p.description like '%" + name + "%'",
                        ProjectDTO.class
                )
                .getResultList();
        return projects;
    }

    @Override
    public @NotNull List<ProjectDTO> findAll() {
        return entityManager.createQuery("from ProjectDTO", ProjectDTO.class).getResultList();
    }

    @Nullable
    @Override
    public ProjectDTO findOne(final @NotNull String id) {
        return entityManager.find(ProjectDTO.class, id);
    }

    @Override
    public void persist(final @NotNull ProjectDTO entity) {
        entityManager.persist(entity);
    }

    @Override
    public void merge(final @NotNull ProjectDTO entity) {
        entityManager.merge(entity);
    }

    @Override
    public void remove(final @NotNull ProjectDTO entity) {
        @Nullable final Project project = entityManager.find(Project.class, entity.getId());
        if (project == null) return;
        entityManager.remove(project);
    }

    @Override
    public void removeAll() {
        entityManager.createQuery("delete from Project").executeUpdate();
    }

}
