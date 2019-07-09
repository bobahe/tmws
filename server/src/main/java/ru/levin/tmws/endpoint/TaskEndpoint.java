package ru.levin.tmws.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.ITaskEndpoint;
import ru.levin.tmws.entity.Project;
import ru.levin.tmws.entity.Session;
import ru.levin.tmws.entity.Task;
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
    public @Nullable Task createTask(final @Nullable Session session, final @Nullable Task task) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        if (session.getUserId() == null || session.getUserId().isEmpty()) throw new SessionValidationException();
        if (task == null) throw new SaveException();
        if (task.getName() == null || task.getName().isEmpty()) throw new SaveException();
        task.setUserId(session.getUserId());
        return serviceLocator.getTaskService().save(task);
    }

    @Override
    public void addTaskToProject(final @Nullable Session session, final @Nullable String taskId,
                                 final @Nullable String projectId) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        if (session.getUserId() == null || session.getUserId().isEmpty()) throw new SessionValidationException();
        if (taskId == null || projectId == null) throw new SaveException();
        @Nullable final Task task = serviceLocator.getTaskService().findOneById(taskId);
        @Nullable final Project project = serviceLocator.getProjectService().findOneById(projectId);
        if (task == null || project == null) throw new SaveException();
        if (task.getUserId() == null || project.getUserId() == null) throw new SaveException();
        if (!task.getUserId().equals(session.getUserId()) || !project.getUserId().equals(session.getUserId())) {
            throw new AccessForbiddenException();
        }
        task.setProjectId(project.getId());
        serviceLocator.getTaskService().update(task);
    }

    @Override
    public void updateTask(final @Nullable Session session, final @Nullable Task task) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        if (session.getUserId() == null || session.getUserId().isEmpty()) throw new SessionValidationException();
        if (task == null) throw new UpdateException();
        if (task.getId() == null || task.getId().isEmpty()) throw new UpdateException();
        if (task.getName() == null || task.getName().isEmpty()) throw new UpdateException();
        serviceLocator.getTaskService().update(task);
    }

    @Override
    public void removeTask(final @Nullable Session session, final @Nullable Task task) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        if (session.getUserId() == null || session.getUserId().isEmpty()) throw new SessionValidationException();
        if (task == null) throw new UpdateException();
        serviceLocator.getTaskService().remove(task);
    }

    @Override
    public void removeTaskAll(final @Nullable Session session) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        if (session.getUserId() == null || session.getUserId().isEmpty()) throw new SessionValidationException();
        serviceLocator.getTaskService().removeByUserId(session.getUserId());
    }

    @Override
    public @NotNull List<Task> getTaskAll(final @Nullable Session session) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        @NotNull final List<Task> allByUserId = serviceLocator.getTaskService().findAllByUserId(session.getUserId());
        return allByUserId;
    }

    @Override
    public @Nullable Task getTaskById(final @Nullable Session session, final int taskId) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        if (session.getUserId() == null || session.getUserId().isEmpty()) throw new SessionValidationException();
        return serviceLocator.getTaskService().findOneByIndex(session.getUserId(), taskId);
    }

    @Override
    public @NotNull List<Task> getTaskByNameOrDescription(final @Nullable Session session, final @Nullable String matcher) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        if (session.getUserId() == null || session.getUserId().isEmpty()) throw new SessionValidationException();
        if (matcher == null) throw new InternalServiceException();
        return serviceLocator.getTaskService().findAllByPartOfNameOrDescription(matcher);
    }

}
