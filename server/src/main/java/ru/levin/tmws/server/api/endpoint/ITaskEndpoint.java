package ru.levin.tmws.server.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.server.entity.Project;
import ru.levin.tmws.server.entity.Session;
import ru.levin.tmws.server.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ITaskEndpoint extends IEndpoint {

    @Nullable
    @WebMethod
    Task createTask(
            @WebParam(name = "ticket") @Nullable final Session session,
            @WebParam(name = "task") @Nullable final Task task
    );

    @WebMethod
    void addTaskToProject(
            @WebParam(name = "ticket") @Nullable final Session session,
            @WebParam(name = "taskId") @Nullable final String taskId,
            @WebParam(name = "projectId") @Nullable final String projectId
    );

    @WebMethod
    void updateTask(
            @WebParam(name = "ticket") @Nullable final Session session,
            @WebParam(name = "task") @Nullable final Task task
    );

    @WebMethod
    void removeTask(
            @WebParam(name = "ticket") @Nullable final Session session,
            @WebParam(name = "task") @Nullable final Task task
    );

    @WebMethod
    void removeTaskAll(@WebParam(name = "ticket") @Nullable final Session session);

    @NotNull
    @WebMethod
    List<Task> getTaskAll(@WebParam(name = "ticket") @Nullable final Session session);

    @Nullable
    @WebMethod
    Task getTaskById(
            @WebParam(name = "ticket") @Nullable final Session session,
            @WebParam(name = "taskId") final int taskId
    );

    @NotNull
    @WebMethod
    List<Task> getTaskByNameOrDescription(
            @WebParam(name = "ticket") @Nullable final Session session,
            @WebParam(name = "matcher") @Nullable final String matcher
    );

}
