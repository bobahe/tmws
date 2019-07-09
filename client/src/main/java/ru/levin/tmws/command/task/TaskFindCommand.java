package ru.levin.tmws.command.task;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.ITaskEndpoint;
import ru.levin.tmws.api.endpoint.Task;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;
import ru.levin.tmws.exception.NoStatusException;

import java.util.List;

public final class TaskFindCommand extends AbstractCommand {

    @NotNull
    private final ITerminalService terminalService;

    @NotNull
    private final ITaskEndpoint taskEndpoint;

    public TaskFindCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
        this.terminalService = serviceLocator.getTerminalService();
        this.taskEndpoint = serviceLocator.getTaskService().getTaskEndpointPort();
    }

    @Override
    @NotNull
    public String getName() {
        return "task-find";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "[TASK FIND]";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Find task by name or description";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() {
        terminalService.println(getTitle());
        terminalService.println("Enter name or description to find: ");
        @NotNull final String matcher = terminalService.getLine();
        @NotNull final List<Task> taskList = taskEndpoint.getTaskByNameOrDescription(serviceLocator.getCurrentSession(), matcher);
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
