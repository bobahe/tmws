package ru.levin.tmws.server.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.server.api.IServiceLocator;
import ru.levin.tmws.server.api.endpoint.ITaskEndpoint;
import ru.levin.tmws.server.entity.Project;
import ru.levin.tmws.server.entity.Session;
import ru.levin.tmws.server.entity.Task;
import ru.levin.tmws.server.exception.*;
import ru.levin.tmws.server.util.ServiceUtil;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.levin.tmws.server.api.endpoint.ITaskEndpoint")
public class TaskEndpoint implements ITaskEndpoint {

    @Nullable
    private IServiceLocator bootstrap;

    public TaskEndpoint() {
    }

    public TaskEndpoint(@Nullable final IServiceLocator bootstrap) {
        this.bootstrap = bootstrap;
    }

    @Override
    public @Nullable Task createTask(final @Nullable Session session, final @Nullable Task task) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        if (session.getUserId() == null || session.getUserId().isEmpty()) throw new SessionValidationException();
        if (task == null) throw new SaveException();
        if (task.getName() == null || task.getName().isEmpty()) throw new SaveException();
        task.setUserId(session.getUserId());
        return bootstrap.getTaskService().save(task);
    }

    @Override
    public void addTaskToProject(final @Nullable Session session, final @Nullable String taskId,
                                 final @Nullable String projectId) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        if (session.getUserId() == null || session.getUserId().isEmpty()) throw new SessionValidationException();
        if (taskId == null || projectId == null) throw new SaveException();
        @Nullable final Task task = bootstrap.getTaskService().findOneById(taskId);
        @Nullable final Project project = bootstrap.getProjectService().findOneById(projectId);
        if (task == null || project == null) throw new SaveException();
        if (task.getUserId() == null || project.getUserId() == null) throw new SaveException();
        if (!task.getUserId().equals(session.getUserId()) || !project.getUserId().equals(session.getUserId())) {
            throw new AccessForbiddenException();
        }
        task.setProjectId(project.getId());
        bootstrap.getTaskService().update(task);
    }

    @Override
    public void updateTask(final @Nullable Session session, final @Nullable Task task) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        if (session.getUserId() == null || session.getUserId().isEmpty()) throw new SessionValidationException();
        if (task == null) throw new UpdateException();
        if (task.getId() == null || task.getId().isEmpty()) throw new UpdateException();
        if (task.getName() == null || task.getName().isEmpty()) throw new UpdateException();
        bootstrap.getTaskService().update(task);
    }

    @Override
    public void removeTask(final @Nullable Session session, final @Nullable Task task) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        if (session.getUserId() == null || session.getUserId().isEmpty()) throw new SessionValidationException();
        if (task == null) throw new UpdateException();
        bootstrap.getTaskService().remove(task);
    }

    @Override
    public void removeTaskAll(final @Nullable Session session) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        if (session.getUserId() == null || session.getUserId().isEmpty()) throw new SessionValidationException();
        bootstrap.getTaskService().removeByUserId(session.getUserId());
    }

    @Override
    public @NotNull List<Task> getTaskAll(final @Nullable Session session) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        @NotNull final List<Task> allByUserId = bootstrap.getTaskService().findAllByUserId(session.getUserId());
        return allByUserId;
    }

    @Override
    public @Nullable Task getTaskById(final @Nullable Session session, final int taskId) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        if (session.getUserId() == null || session.getUserId().isEmpty()) throw new SessionValidationException();
        return bootstrap.getTaskService().findOneByIndex(session.getUserId(), taskId);
    }

    @Override
    public @NotNull List<Task> getTaskByNameOrDescription(final @Nullable Session session, final @Nullable String matcher) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        if (session.getUserId() == null || session.getUserId().isEmpty()) throw new SessionValidationException();
        if (matcher == null) throw new InternalServiceException();
        return bootstrap.getTaskService().findAllByPartOfNameOrDescription(matcher);
    }

}
