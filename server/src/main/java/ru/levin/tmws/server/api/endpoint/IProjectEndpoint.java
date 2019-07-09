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
public interface IProjectEndpoint extends IEndpoint {

    @Nullable
    @WebMethod
    Project createProject(
            @WebParam(name = "ticket") @Nullable final Session session,
            @WebParam(name = "project") @Nullable final Project project
    );

    @WebMethod
    void updateProject(
            @WebParam(name = "ticket") @Nullable final Session session,
            @WebParam(name = "project") @Nullable final Project project
    );

    @WebMethod
    void removeProject(
            @WebParam(name = "ticket") @Nullable final Session session,
            @WebParam(name = "project") @Nullable final Project project
    );

    @WebMethod
    void removeProjectAll(@WebParam(name = "ticket") @Nullable final Session session);

    @NotNull
    @WebMethod
    List<Project> getProjectAll(@WebParam(name = "ticket") @Nullable final Session session);

    @NotNull
    @WebMethod
    List<Task> getProjectTasks(
            @WebParam(name = "ticket") @Nullable final Session session,
            @WebParam(name = "project") @Nullable final Project project
    );

    @Nullable
    @WebMethod
    Project getProjectById(
            @WebParam(name = "ticket") @Nullable final Session session,
            @WebParam(name = "projectId") final int projectId
    );

    @NotNull
    @WebMethod
    List<Project> getProjectByNameOrDescription(
            @WebParam(name = "ticket") @Nullable final Session session,
            @WebParam(name = "matcher") @Nullable final String matcher
    );

}
