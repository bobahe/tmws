package ru.levin.tmws.client.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.client.api.IServiceLocator;
import ru.levin.tmws.client.api.service.ITerminalService;
import ru.levin.tmws.client.command.AbstractCommand;
import ru.levin.tmws.client.exception.NoSelectedTaskException;
import ru.levin.tmws.server.api.endpoint.ITaskEndpoint;
import ru.levin.tmws.server.api.endpoint.Task;

public final class TaskRemoveSelectedCommand extends AbstractCommand {

    @NotNull
    private final ITerminalService terminalService;

    @NotNull
    private final ITaskEndpoint taskEndpoint;

    public TaskRemoveSelectedCommand(@NotNull final IServiceLocator bootstrap) {
        super(bootstrap);
        this.terminalService = bootstrap.getTerminalService();
        this.taskEndpoint = bootstrap.getTaskService().getTaskEndpointPort();
    }

    @Override
    @NotNull
    public String getName() {
        return "task-remove";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Remove selected task";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() {
        @Nullable final Task selectedTask = bootstrap.getSelectedTask();
        if (selectedTask == null) throw new NoSelectedTaskException();
        taskEndpoint.removeTask(bootstrap.getCurrentSession(), selectedTask);
        bootstrap.setSelectedTask(null);
    }

}
