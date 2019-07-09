package ru.levin.tmws.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.endpoint.Project;
import ru.levin.tmws.api.endpoint.Session;
import ru.levin.tmws.api.endpoint.Task;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;
import ru.levin.tmws.endpoint.*;

import java.util.Map;

public interface IServiceLocator {

    @NotNull Map<String, AbstractCommand> getCommands();
    @NotNull ITerminalService getTerminalService();
    @NotNull AdminEndpointService getAdminService();
    @NotNull UserEndpointService getUserService();
    @NotNull ProjectEndpointService getProjectService();
    @NotNull TaskEndpointService getTaskService();
    @NotNull SessionEndpointService getSessionService();
    @Nullable Session getCurrentSession();
    void setCurrentSession(final Session session);
    @Nullable Project getSelectedProject();
    void setSelectedProject(final Project project);
    @Nullable Task getSelectedTask();
    void setSelectedTask(final Task task);

}
