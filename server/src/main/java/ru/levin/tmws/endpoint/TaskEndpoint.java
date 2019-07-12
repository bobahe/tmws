package ru.levin.tmws.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.ITaskEndpoint;
import ru.levin.tmws.dto.ProjectDTO;
import ru.levin.tmws.dto.SessionDTO;
import ru.levin.tmws.dto.TaskDTO;
import ru.levin.tmws.exception.*;
import ru.levin.tmws.util.ServiceUtil;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.levin.tmws.api.endpoint.ITaskEndpoint")
public class TaskEndpoint implements ITaskEndpoint {

    @Nullable
    private IServiceLocator serviceLocator;

    public TaskEndpoint() {
    }

    public TaskEndpoint(@Nullable final IServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public @Nullable TaskDTO createTask(final @Nullable SessionDTO session, final @Nullable TaskDTO task) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        if (session.getUserId() == null) throw new SessionValidationException();
        if (task == null) throw new SaveException();
        if (task.getName() == null || task.getName().isEmpty()) throw new SaveException();
        task.setUserId(session.getUserId());
        return serviceLocator.getTaskService().save(task);
    }

    @Override
    public void addTaskToProject(final @Nullable SessionDTO session, final @Nullable String taskId,
                                 final @Nullable String projectId) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        if (session.getUserId() == null) throw new SessionValidationException();
        if (taskId == null || projectId == null) throw new SaveException();
        @Nullable final TaskDTO task = serviceLocator.getTaskService().findOneById(taskId);
        @Nullable final ProjectDTO project = serviceLocator.getProjectService().findOneById(projectId);
        if (task == null || project == null) throw new SaveException();
        if (task.getUserId() == null || project.getUserId() == null) throw new SaveException();
        if (!task.getUserId().equals(session.getUserId()) ||
                !project.getUserId().equals(session.getUserId())) {
            throw new AccessForbiddenException();
        }
        task.setProjectId(project.getId());
        serviceLocator.getTaskService().update(task);
    }

    @Override
    public void updateTask(final @Nullable SessionDTO session, final @Nullable TaskDTO task) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        if (session.getUserId() == null) throw new SessionValidationException();
        if (task == null) throw new UpdateException();
        if (task.getId() == null || task.getId().isEmpty()) throw new UpdateException();
        if (task.getName() == null || task.getName().isEmpty()) throw new UpdateException();
        serviceLocator.getTaskService().update(task);
    }

    @Override
    public void removeTask(final @Nullable SessionDTO session, final @Nullable TaskDTO task) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        if (session.getUserId() == null) throw new SessionValidationException();
        if (task == null) throw new UpdateException();
        serviceLocator.getTaskService().remove(task);
    }

    @Override
    public void removeTaskAll(final @Nullable SessionDTO session) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        if (session.getUserId() == null) throw new SessionValidationException();
        serviceLocator.getTaskService().removeByUserId(session.getUserId());
    }

    @Override
    public @NotNull List<TaskDTO> getTaskAll(final @Nullable SessionDTO session) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        if (session.getUserId() == null) throw new InternalServiceException();
        @NotNull final List<TaskDTO> allByUserId = serviceLocator.getTaskService().findAllByUserId(session.getUserId());
        return allByUserId;
    }

    @Override
    public @Nullable TaskDTO getTaskById(final @Nullable SessionDTO session, final int taskId) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        if (session.getUserId() == null) throw new SessionValidationException();
        return serviceLocator.getTaskService().findOneByIndex(session.getUserId(), taskId);
    }

    @Override
    public @NotNull List<TaskDTO> getTaskByNameOrDescription(final @Nullable SessionDTO session, final @Nullable String matcher) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        if (session.getUserId() == null) throw new SessionValidationException();
        if (matcher == null) throw new InternalServiceException();
        return serviceLocator.getTaskService().findAllByPartOfNameOrDescription(matcher);
    }

}
