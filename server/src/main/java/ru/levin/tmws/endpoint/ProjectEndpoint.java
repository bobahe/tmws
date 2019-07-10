package ru.levin.tmws.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.IProjectEndpoint;
import ru.levin.tmws.entity.Project;
import ru.levin.tmws.entity.Session;
import ru.levin.tmws.entity.Task;
import ru.levin.tmws.exception.*;
import ru.levin.tmws.util.ServiceUtil;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.levin.tmws.api.endpoint.IProjectEndpoint")
public class ProjectEndpoint implements IProjectEndpoint {

    @Nullable
    private IServiceLocator serviceLocator;

    public ProjectEndpoint() {
    }

    public ProjectEndpoint(@NotNull final IServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public @Nullable Project createProject(final @Nullable Session session, final @Nullable Project project) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        if (project == null) throw new SaveException();
        if (project.getName() == null || project.getName().isEmpty()) throw new SaveException();
        if (session.getUserId() == null) throw new SessionValidationException();
        project.setUserId(session.getUserId());
        return serviceLocator.getProjectService().save(project);
    }

    @Override
    public void updateProject(final @Nullable Session session, final @Nullable Project project) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        if (project == null) throw new UpdateException();
        if (project.getId() == null || project.getId().isEmpty()) throw new UpdateException();
        serviceLocator.getProjectService().update(project);
    }

    @Override
    public void removeProject(final @Nullable Session session, final @Nullable Project project) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        if (project == null) throw new DeleteException();
        if (project.getId() == null || project.getId().isEmpty()) throw new DeleteException();
        serviceLocator.getProjectService().remove(project);
    }

    @Override
    public void removeProjectAll(final @Nullable Session session) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        serviceLocator.getProjectService().removeByUserId(session.getUserId());
    }

    @Override
    public @NotNull List<Project> getProjectAll(final @Nullable Session session) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        @NotNull final List<Project> allByUserId = serviceLocator.getProjectService().findAllByUserId(session.getUserId());
        return allByUserId;
    }

    @Override
    public @NotNull List<Task> getProjectTasks(final @Nullable Session session, final @Nullable Project project) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        if (project == null) throw new NoSelectedProjectException();
        if (project.getId() == null || project.getId().isEmpty()) throw new NoSelectedProjectException();
        if (session.getUserId() == null) throw new SessionValidationException();
        if (project.getUserId() == null || !session.getUserId().equals(project.getUserId())) {
            throw new AccessForbiddenException();
        }
        return serviceLocator.getTaskService().findAllByUserIdAndProjectId(project.getUserId(), project.getId());
    }

    @Override
    public @Nullable Project getProjectById(final @Nullable Session session, final int projectId) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        if (session.getUserId() == null || session.getUserId().isEmpty()) throw new SessionValidationException();
        return serviceLocator.getProjectService().findOneByIndex(session.getUserId(), projectId);
    }

    @Override
    public @NotNull List<Project> getProjectByNameOrDescription(final @Nullable Session session, final @Nullable String matcher) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        if (session.getUserId() == null || session.getUserId().isEmpty()) throw new SessionValidationException();
        if (matcher == null) throw new InternalServiceException();
        return serviceLocator.getProjectService().findAllByPartOfNameOrDescription(matcher);
    }

}