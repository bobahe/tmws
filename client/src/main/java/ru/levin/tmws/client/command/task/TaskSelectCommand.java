package ru.levin.tmws.client.command.task;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.client.api.IServiceLocator;
import ru.levin.tmws.client.api.service.ITerminalService;
import ru.levin.tmws.client.command.AbstractCommand;
import ru.levin.tmws.client.exception.NoSuchItemException;
import ru.levin.tmws.server.api.endpoint.ITaskEndpoint;

public final class TaskSelectCommand extends AbstractCommand {

    @NotNull
    private static final String SELECTED_TASK_MESSAGE = "SELECTED TASK: ";

    @NotNull
    private static final String SERIAL_NUMBER_PROMPT = "ENTER SERIAL NUMBER:";

    @NotNull
    private final ITerminalService terminalService;

    @NotNull
    private final ITaskEndpoint taskEndpoint;

    public TaskSelectCommand(@NotNull final IServiceLocator bootstrap) {
        super(bootstrap);
        this.terminalService = bootstrap.getTerminalService();
        this.taskEndpoint = bootstrap.getTaskService().getTaskEndpointPort();
    }

    @Override
    @NotNull
    public String getName() {
        return "task-select";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "[TASK SELECT]";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Select task";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() {
        terminalService.println(this.getTitle());
        terminalService.println(SERIAL_NUMBER_PROMPT);
        final int index = Integer.parseInt(terminalService.getLine());
        bootstrap.setSelectedTask(taskEndpoint.getTaskById(bootstrap.getCurrentSession(), index));
        if (bootstrap.getSelectedTask() == null) throw new NoSuchItemException();
        terminalService.println(SELECTED_TASK_MESSAGE + bootstrap.getSelectedTask().getName());
    }

}
