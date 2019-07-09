package ru.levin.tmws.client.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.client.api.IServiceLocator;
import ru.levin.tmws.client.api.service.ITerminalService;
import ru.levin.tmws.client.command.AbstractCommand;
import ru.levin.tmws.client.exception.NoSelectedProjectException;
import ru.levin.tmws.client.exception.NoSelectedTaskException;
import ru.levin.tmws.server.api.endpoint.ITaskEndpoint;
import ru.levin.tmws.server.api.endpoint.Project;
import ru.levin.tmws.server.api.endpoint.Task;

public final class TaskJoinCommand extends AbstractCommand {

    @NotNull
    private static final String SELECTED_PROJECT_MESSAGE = "SELECTED TASK: ";

    @NotNull
    private static final String SELECTED_TASK_MESSAGE = "SELECTED TASK: ";

    @NotNull
    private final ITerminalService terminalService;

    @NotNull
    private final ITaskEndpoint taskEndpoint;

    public TaskJoinCommand(@NotNull final IServiceLocator bootstrap) {
        super(bootstrap);
        this.terminalService = bootstrap.getTerminalService();
        this.taskEndpoint = bootstrap.getTaskService().getTaskEndpointPort();
    }

    @Override
    @NotNull
    public String getName() {
        return "task-join";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "[JOIN TASK TO PROJECT]";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Join selected task to selected project";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() {
        @Nullable final Project selectedProject = bootstrap.getSelectedProject();
        @Nullable final Task selectedTask = bootstrap.getSelectedTask();
        if (selectedProject == null || selectedProject.getId() == null) throw new NoSelectedProjectException();
        if (selectedTask == null) throw new NoSelectedTaskException();

        terminalService.println(this.getTitle());
        terminalService.println(SELECTED_PROJECT_MESSAGE + selectedProject);
        terminalService.println(SELECTED_TASK_MESSAGE + selectedTask);
        terminalService.println(getDescription() + "? (Y/n)");
        @NotNull final String joinAnswer = terminalService.getLine();
        if (!"n".equals(joinAnswer)) selectedTask.setProjectId(selectedProject.getId());

        taskEndpoint.updateTask(bootstrap.getCurrentSession(), selectedTask);
    }

}
