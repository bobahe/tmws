package ru.levin.tmws.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.endpoint.IProjectEndpoint;
import ru.levin.tmws.api.service.IProjectService;
import ru.levin.tmws.api.service.ISessionService;
import ru.levin.tmws.api.service.ITaskService;
import ru.levin.tmws.dto.ProjectDTO;
import ru.levin.tmws.dto.SessionDTO;
import ru.levin.tmws.dto.TaskDTO;
import ru.levin.tmws.exception.*;
import ru.levin.tmws.util.ServiceUtil;

import javax.inject.Inject;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.levin.tmws.api.endpoint.IProjectEndpoint")
public class ProjectEndpoint implements IProjectEndpoint {

    @Nullable
    @Inject
    private ISessionService sessionService;

    @Nullable
    @Inject
    private IProjectService projectService;

    @Nullable
    @Inject
    private ITaskService taskService;

    @Override
    public @Nullable ProjectDTO createProject(final @Nullable SessionDTO session, final @Nullable ProjectDTO project) {
        if (sessionService == null || projectService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (project == null) throw new SaveException();
        if (project.getName() == null || project.getName().isEmpty()) throw new SaveException();
        if (session.getUserId() == null) throw new SessionValidationException();
        project.setUserId(session.getUserId());
        return projectService.save(project);
    }

    @Override
    public void updateProject(final @Nullable SessionDTO session, final @Nullable ProjectDTO project) {
        if (sessionService == null || projectService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (project == null) throw new UpdateException();
        if (project.getId() == null || project.getId().isEmpty()) throw new UpdateException();
        projectService.update(project);
    }

    @Override
    public void removeProject(final @Nullable SessionDTO session, final @Nullable ProjectDTO project) {
        if (sessionService == null || projectService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (project == null) throw new DeleteException();
        if (project.getId() == null || project.getId().isEmpty()) throw new DeleteException();
        projectService.remove(project);
    }

    @Override
    public void removeProjectAll(final @Nullable SessionDTO session) {
        if (sessionService == null || projectService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new InternalServiceException();
        projectService.removeByUserId(session.getUserId());
    }

    @Override
    public @NotNull List<ProjectDTO> getProjectAll(final @Nullable SessionDTO session) {
        if (sessionService == null || projectService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new InternalServiceException();
        @NotNull final List<ProjectDTO> allByUserId = projectService.findAllByUserId(session.getUserId());
        return allByUserId;
    }

    @Override
    public @NotNull List<TaskDTO> getProjectTasks(final @Nullable SessionDTO session, final @Nullable ProjectDTO project) {
        if (sessionService == null || projectService == null || taskService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (project == null) throw new NoSelectedProjectException();
        if (project.getId() == null || project.getId().isEmpty()) throw new NoSelectedProjectException();
        if (session.getUserId() == null) throw new SessionValidationException();
        if (project.getUserId() == null || !session.getUserId().equals(project.getUserId())) {
            throw new AccessForbiddenException();
        }
        return taskService.findAllByUserIdAndProjectId(project.getUserId(), project.getId());
    }

    @Override
    public @Nullable ProjectDTO getProjectById(final @Nullable SessionDTO session, final int projectId) {
        if (sessionService == null || projectService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new SessionValidationException();
        return projectService.findOneByIndex(session.getUserId(), projectId);
    }

    @Override
    public @NotNull List<ProjectDTO> getProjectByNameOrDescription(final @Nullable SessionDTO session, final @Nullable String matcher) {
        if (sessionService == null || projectService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new SessionValidationException();
        if (matcher == null) throw new InternalServiceException();
        return projectService.findAllByPartOfNameOrDescription(matcher);
    }

}
