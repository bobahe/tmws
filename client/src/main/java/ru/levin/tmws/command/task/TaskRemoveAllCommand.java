package ru.levin.tmws.command.task;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.ITaskEndpoint;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;

public final class TaskRemoveAllCommand extends AbstractCommand {

    @NotNull
    private static final String ALL_TASK_REMOVED_MESSAGE = "[ALL TASKS REMOVED]\n";

    @NotNull
    private final ITerminalService terminalService;

    @NotNull
    private final ITaskEndpoint taskEndpoint;

    public TaskRemoveAllCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
        this.terminalService = serviceLocator.getTerminalService();
        this.taskEndpoint = serviceLocator.getTaskService().getTaskEndpointPort();
    }

    @Override
    @NotNull
    public String getName() {
        return "task-clear";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Remove all tasks";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() {
        taskEndpoint.removeTaskAll(serviceLocator.getCurrentSession());
        serviceLocator.setSelectedTask(null);
        terminalService.println(ALL_TASK_REMOVED_MESSAGE);
    }

}
