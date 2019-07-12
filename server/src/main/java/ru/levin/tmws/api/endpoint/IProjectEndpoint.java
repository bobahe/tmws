package ru.levin.tmws.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.dto.ProjectDTO;
import ru.levin.tmws.dto.SessionDTO;
import ru.levin.tmws.dto.TaskDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface IProjectEndpoint extends IEndpoint {

    @Nullable
    @WebMethod
    ProjectDTO createProject(
            @WebParam(name = "ticket") @Nullable final SessionDTO session,
            @WebParam(name = "project") @Nullable final ProjectDTO project
    );

    @WebMethod
    void updateProject(
            @WebParam(name = "ticket") @Nullable final SessionDTO session,
            @WebParam(name = "project") @Nullable final ProjectDTO project
    );

    @WebMethod
    void removeProject(
            @WebParam(name = "ticket") @Nullable final SessionDTO session,
            @WebParam(name = "project") @Nullable final ProjectDTO project
    );

    @WebMethod
    void removeProjectAll(@WebParam(name = "ticket") @Nullable final SessionDTO session);

    @NotNull
    @WebMethod
    List<ProjectDTO> getProjectAll(@WebParam(name = "ticket") @Nullable final SessionDTO session);

    @NotNull
    @WebMethod
    List<TaskDTO> getProjectTasks(
            @WebParam(name = "ticket") @Nullable final SessionDTO session,
            @WebParam(name = "project") @Nullable final ProjectDTO project
    );

    @Nullable
    @WebMethod
    ProjectDTO getProjectById(
            @WebParam(name = "ticket") @Nullable final SessionDTO session,
            @WebParam(name = "projectId") final int projectId
    );

    @NotNull
    @WebMethod
    List<ProjectDTO> getProjectByNameOrDescription(
            @WebParam(name = "ticket") @Nullable final SessionDTO session,
            @WebParam(name = "matcher") @Nullable final String matcher
    );

}
