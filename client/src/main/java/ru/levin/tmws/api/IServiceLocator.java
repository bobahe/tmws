package ru.levin.tmws.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.endpoint.ProjectDTO;
import ru.levin.tmws.api.endpoint.SessionDTO;
import ru.levin.tmws.api.endpoint.TaskDTO;
import ru.levin.tmws.command.AbstractCommand;

import java.util.Map;

public interface IServiceLocator {

    @NotNull Map<String, AbstractCommand> getCommands();
    @Nullable SessionDTO getCurrentSession();
    void setCurrentSession(final SessionDTO session);
    @Nullable ProjectDTO getSelectedProject();
    void setSelectedProject(final ProjectDTO project);
    @Nullable TaskDTO getSelectedTask();
    void setSelectedTask(final TaskDTO task);

}
