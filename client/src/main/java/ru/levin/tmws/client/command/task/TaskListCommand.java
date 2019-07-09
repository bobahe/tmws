package ru.levin.tmws.client.command.task;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.client.api.IServiceLocator;
import ru.levin.tmws.client.api.service.ITerminalService;
import ru.levin.tmws.client.command.AbstractCommand;
import ru.levin.tmws.client.exception.NoStatusException;
import ru.levin.tmws.client.util.CommandUtil;
import ru.levin.tmws.server.api.endpoint.ITaskEndpoint;
import ru.levin.tmws.server.api.endpoint.Task;

import java.util.List;

public final class TaskListCommand extends AbstractCommand {

    @NotNull
    private final ITerminalService terminalService;

    @NotNull
    private final ITaskEndpoint taskEndpoint;

    public TaskListCommand(@NotNull final IServiceLocator bootstrap) {
        super(bootstrap);
        this.terminalService = bootstrap.getTerminalService();
        this.taskEndpoint = bootstrap.getTaskService().getTaskEndpointPort();
    }

    @Override
    @NotNull
    public String getName() {
        return "task-list";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "[TASK LIST]";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Show all tasks";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() {
        @NotNull final List<Task> taskList = taskEndpoint.getTaskAll(bootstrap.getCurrentSession());
        terminalService.println("Select option to sortTaskList list (1 by default):");
        terminalService.println("1. Saved order");
        terminalService.println("2. Start date");
        terminalService.println("3. End date");
        terminalService.println("4. Status");
        @NotNull final String orderType = terminalService.getLine();
        CommandUtil.sortTaskList(orderType, taskList);
        for (int i = 0; i < taskList.size(); i++) {
            @NotNull final Task task = taskList.get(i);
            if (task.getStatus() == null) throw new NoStatusException();
            terminalService.println((i + 1) + ". " + task.getName());
            terminalService.println("\tDescription: " + task.getDescription());
            if (task.getStartDate() != null) {
                terminalService.println("\tStart date: " + task.getStartDate());
            } else {
                terminalService.println("\tStar date: not set");
            }
            if (task.getEndDate() != null) {
                terminalService.println("\tEnd date: " + task.getEndDate());
            } else {
                terminalService.println("\tEnd date: not set");
            }

            terminalService.println("\tProject: " + task.getProjectId());
            terminalService.println("\tStatus: " + task.getStatus().value());
        }
    }

}
