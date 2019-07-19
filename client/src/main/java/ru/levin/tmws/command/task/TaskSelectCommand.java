package ru.levin.tmws.command.task;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.ITaskEndpoint;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;
import ru.levin.tmws.exception.NoSuchItemException;

@Component
public final class TaskSelectCommand extends AbstractCommand {

    @NotNull
    private static final String SELECTED_TASK_MESSAGE = "SELECTED TASK: ";

    @NotNull
    private static final String SERIAL_NUMBER_PROMPT = "ENTER SERIAL NUMBER:";

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
    public TaskSelectCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
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
        serviceLocator.setSelectedTask(taskEndpoint.getTaskById(serviceLocator.getCurrentSession(), index));
        if (serviceLocator.getSelectedTask() == null) throw new NoSuchItemException();
        terminalService.println(SELECTED_TASK_MESSAGE + serviceLocator.getSelectedTask().getName());
    }

}
