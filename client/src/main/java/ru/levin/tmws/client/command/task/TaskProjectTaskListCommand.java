package ru.levin.tmws.client.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.client.api.IServiceLocator;
import ru.levin.tmws.client.api.service.ITerminalService;
import ru.levin.tmws.client.command.AbstractCommand;
import ru.levin.tmws.client.exception.NoSelectedProjectException;
import ru.levin.tmws.client.exception.NoStatusException;
import ru.levin.tmws.server.api.endpoint.IProjectEndpoint;
import ru.levin.tmws.server.api.endpoint.Project;
import ru.levin.tmws.server.api.endpoint.Task;

import java.util.List;

public final class TaskProjectTaskListCommand extends AbstractCommand {

    @NotNull
    private final ITerminalService terminalService;

    @NotNull
    private final IProjectEndpoint projectEndpoint;

    public TaskProjectTaskListCommand(@NotNull final IServiceLocator bootstrap) {
        super(bootstrap);
        this.terminalService = bootstrap.getTerminalService();
        this.projectEndpoint = bootstrap.getProjectService().getProjectEndpointPort();
    }

    @Override
    @NotNull
    public String getName() {
        return "project-task-list";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "[PROJECT TASK LIST]";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Show all tasks for selected project";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() {
        @Nullable final Project selectedProject = bootstrap.getSelectedProject();
        if (selectedProject == null) throw new NoSelectedProjectException();

        terminalService.println(getTitle() + " for " + selectedProject.getName());
        @NotNull final List<Task> taskList = projectEndpoint.getProjectTasks(bootstrap.getCurrentSession(), selectedProject);

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
            terminalService.println("\tStatus: " + task.getStatus().value());
        }
    }

}
