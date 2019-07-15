package ru.levin.tmws.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.endpoint.ITaskEndpoint;
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

@WebService(endpointInterface = "ru.levin.tmws.api.endpoint.ITaskEndpoint")
public class TaskEndpoint implements ITaskEndpoint {

    @Nullable
    @Inject
    private ISessionService sessionService;

    @Nullable
    @Inject
    private ITaskService taskService;

    @Nullable
    @Inject
    private IProjectService projectService;

    @Override
    public @Nullable TaskDTO createTask(final @Nullable SessionDTO session, final @Nullable TaskDTO task) {
        if (sessionService == null || taskService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new SessionValidationException();
        if (task == null) throw new SaveException();
        if (task.getName() == null || task.getName().isEmpty()) throw new SaveException();
        task.setUserId(session.getUserId());
        return taskService.save(task);
    }

    @Override
    public void addTaskToProject(final @Nullable SessionDTO session, final @Nullable String taskId,
                                 final @Nullable String projectId) {
        if (sessionService == null || taskService == null || projectService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new SessionValidationException();
        if (taskId == null || projectId == null) throw new SaveException();
        @Nullable final TaskDTO task = taskService.findOneById(taskId);
        @Nullable final ProjectDTO project = projectService.findOneById(projectId);
        if (task == null || project == null) throw new SaveException();
        if (task.getUserId() == null || project.getUserId() == null) throw new SaveException();
        if (!task.getUserId().equals(session.getUserId()) ||
                !project.getUserId().equals(session.getUserId())) {
            throw new AccessForbiddenException();
        }
        task.setProjectId(project.getId());
        taskService.update(task);
    }

    @Override
    public void updateTask(final @Nullable SessionDTO session, final @Nullable TaskDTO task) {
        if (sessionService == null || taskService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new SessionValidationException();
        if (task == null) throw new UpdateException();
        if (task.getId() == null || task.getId().isEmpty()) throw new UpdateException();
        if (task.getName() == null || task.getName().isEmpty()) throw new UpdateException();
        taskService.update(task);
    }

    @Override
    public void removeTask(final @Nullable SessionDTO session, final @Nullable TaskDTO task) {
        if (sessionService == null || taskService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new SessionValidationException();
        if (task == null) throw new UpdateException();
        taskService.remove(task);
    }

    @Override
    public void removeTaskAll(final @Nullable SessionDTO session) {
        if (sessionService == null || taskService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new SessionValidationException();
        taskService.removeByUserId(session.getUserId());
    }

    @Override
    public @NotNull List<TaskDTO> getTaskAll(final @Nullable SessionDTO session) {
        if (sessionService == null || taskService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new InternalServiceException();
        @NotNull final List<TaskDTO> allByUserId = taskService.findAllByUserId(session.getUserId());
        return allByUserId;
    }

    @Override
    public @Nullable TaskDTO getTaskById(final @Nullable SessionDTO session, final int taskId) {
        if (sessionService == null || taskService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new SessionValidationException();
        return taskService.findOneByIndex(session.getUserId(), taskId);
    }

    @Override
    public @NotNull List<TaskDTO> getTaskByNameOrDescription(final @Nullable SessionDTO session, final @Nullable String matcher) {
        if (sessionService == null || taskService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new SessionValidationException();
        if (matcher == null) throw new InternalServiceException();
        return taskService.findAllByPartOfNameOrDescription(matcher);
    }

}
