package ru.levin.tmws.command.task;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.ITaskEndpoint;
import ru.levin.tmws.api.endpoint.TaskDTO;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;
import ru.levin.tmws.exception.NoStatusException;
import ru.levin.tmws.util.CommandUtil;

import java.util.List;

@Component
public final class TaskListCommand extends AbstractCommand {

    @NotNull
    private ITerminalService terminalService;
    @Autowired
    public void setTerminalService(@NotNull final ITerminalService terminalService) {
        this.terminalService = terminalService;
    }

    @NotNull
    private ITaskEndpoint taskEndpoint;
    @Autowired
    public void setTaskEndpoint(@NotNull final ITaskEndpoint taskEndpoint) {
        this.taskEndpoint = taskEndpoint;
    }

    @Autowired
    public TaskListCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
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
        @NotNull final List<TaskDTO> taskList = taskEndpoint.getTaskAll(serviceLocator.getCurrentSession());
        terminalService.println("Select option to sortTaskList list (1 by default):");
        terminalService.println("1. Saved order");
        terminalService.println("2. Start date");
        terminalService.println("3. End date");
        terminalService.println("4. Status");
        @NotNull final String orderType = terminalService.getLine();
        CommandUtil.sortTaskList(orderType, taskList);
        for (int i = 0; i < taskList.size(); i++) {
            @NotNull final TaskDTO task = taskList.get(i);
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
            terminalService.println("\tStatus: " + task.getStatus());
        }
    }

}
