package ru.levin.tmws.server.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.server.api.IServiceLocator;
import ru.levin.tmws.server.api.endpoint.IProjectEndpoint;
import ru.levin.tmws.server.entity.Project;
import ru.levin.tmws.server.entity.Session;
import ru.levin.tmws.server.entity.Task;
import ru.levin.tmws.server.exception.*;
import ru.levin.tmws.server.util.ServiceUtil;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.levin.tmws.server.api.endpoint.IProjectEndpoint")
public class ProjectEndpoint implements IProjectEndpoint {

    @Nullable
    private IServiceLocator bootstrap;

    public ProjectEndpoint() {
    }

    public ProjectEndpoint(@NotNull final IServiceLocator bootstrap) {
        this.bootstrap = bootstrap;
    }

    @Override
    public @Nullable Project createProject(final @Nullable Session session, final @Nullable Project project) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        if (project == null) throw new SaveException();
        if (project.getName() == null || project.getName().isEmpty()) throw new SaveException();
        if (session.getUserId() == null) throw new SessionValidationException();
        project.setUserId(session.getUserId());
        return bootstrap.getProjectService().save(project);
    }

    @Override
    public void updateProject(final @Nullable Session session, final @Nullable Project project) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        if (project == null) throw new UpdateException();
        if (project.getId() == null || project.getId().isEmpty()) throw new UpdateException();
        bootstrap.getProjectService().update(project);
    }

    @Override
    public void removeProject(final @Nullable Session session, final @Nullable Project project) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        if (project == null) throw new DeleteException();
        if (project.getId() == null || project.getId().isEmpty()) throw new DeleteException();
        bootstrap.getProjectService().remove(project);
    }

    @Override
    public void removeProjectAll(final @Nullable Session session) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        bootstrap.getProjectService().removeByUserId(session.getUserId());
    }

    @Override
    public @NotNull List<Project> getProjectAll(final @Nullable Session session) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        @NotNull final List<Project> allByUserId = bootstrap.getProjectService().findAllByUserId(session.getUserId());
        return allByUserId;
    }

    @Override
    public @NotNull List<Task> getProjectTasks(final @Nullable Session session, final @Nullable Project project) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        if (project == null) throw new NoSelectedProjectException();
        if (project.getId() == null || project.getId().isEmpty()) throw new NoSelectedProjectException();
        if (session.getUserId() == null) throw new SessionValidationException();
        if (project.getUserId() == null || !session.getUserId().equals(project.getUserId())) {
            throw new AccessForbiddenException();
        }
        return bootstrap.getTaskService().findAllByUserIdAndProjectId(project.getUserId(), project.getId());
    }

    @Override
    public @Nullable Project getProjectById(final @Nullable Session session, final int projectId) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        if (session.getUserId() == null || session.getUserId().isEmpty()) throw new SessionValidationException();
        return bootstrap.getProjectService().findOneByIndex(session.getUserId(), projectId);
    }

    @Override
    public @NotNull List<Project> getProjectByNameOrDescription(final @Nullable Session session, final @Nullable String matcher) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        if (session.getUserId() == null || session.getUserId().isEmpty()) throw new SessionValidationException();
        if (matcher == null) throw new InternalServiceException();
        return bootstrap.getProjectService().findAllByPartOfNameOrDescription(matcher);
    }

}
