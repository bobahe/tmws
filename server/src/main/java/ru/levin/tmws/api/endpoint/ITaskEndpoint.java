package ru.levin.tmws.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.dto.SessionDTO;
import ru.levin.tmws.dto.TaskDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ITaskEndpoint extends IEndpoint {

    @Nullable
    @WebMethod
    TaskDTO createTask(
            @WebParam(name = "ticket") @Nullable final SessionDTO session,
            @WebParam(name = "task") @Nullable final TaskDTO task
    );

    @WebMethod
    void addTaskToProject(
            @WebParam(name = "ticket") @Nullable final SessionDTO session,
            @WebParam(name = "taskId") @Nullable final String taskId,
            @WebParam(name = "projectId") @Nullable final String projectId
    );

    @WebMethod
    void updateTask(
            @WebParam(name = "ticket") @Nullable final SessionDTO session,
            @WebParam(name = "task") @Nullable final TaskDTO task
    );

    @WebMethod
    void removeTask(
            @WebParam(name = "ticket") @Nullable final SessionDTO session,
            @WebParam(name = "task") @Nullable final TaskDTO task
    );

    @WebMethod
    void removeTaskAll(@WebParam(name = "ticket") @Nullable final SessionDTO session);

    @NotNull
    @WebMethod
    List<TaskDTO> getTaskAll(@WebParam(name = "ticket") @Nullable final SessionDTO session);

    @Nullable
    @WebMethod
    TaskDTO getTaskById(
            @WebParam(name = "ticket") @Nullable final SessionDTO session,
            @WebParam(name = "taskId") final int taskId
    );

    @NotNull
    @WebMethod
    List<TaskDTO> getTaskByNameOrDescription(
            @WebParam(name = "ticket") @Nullable final SessionDTO session,
            @WebParam(name = "matcher") @Nullable final String matcher
    );

}
