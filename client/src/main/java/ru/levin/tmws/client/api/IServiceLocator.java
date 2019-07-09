package ru.levin.tmws.client.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.client.api.service.ITerminalService;
import ru.levin.tmws.client.command.AbstractCommand;
import ru.levin.tmws.server.api.endpoint.Project;
import ru.levin.tmws.server.api.endpoint.Session;
import ru.levin.tmws.server.api.endpoint.Task;
import ru.levin.tmws.server.endpoint.*;

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
