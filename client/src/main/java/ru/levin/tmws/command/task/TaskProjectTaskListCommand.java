package ru.levin.tmws.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.IProjectEndpoint;
import ru.levin.tmws.api.endpoint.ProjectDTO;
import ru.levin.tmws.api.endpoint.TaskDTO;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;
import ru.levin.tmws.exception.NoSelectedProjectException;
import ru.levin.tmws.exception.NoStatusException;

import java.util.List;

@Component
public final class TaskProjectTaskListCommand extends AbstractCommand {

    @NotNull
    private ITerminalService terminalService;
    @Autowired
    public void setTerminalService(@NotNull final ITerminalService terminalService) {
        this.terminalService = terminalService;
    }

    @NotNull
    private IProjectEndpoint projectEndpoint;
    @Autowired
    public void setProjectEndpoint(@NotNull final IProjectEndpoint projectEndpoint) {
        this.projectEndpoint = projectEndpoint;
    }

    @Autowired
    public TaskProjectTaskListCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
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
        @Nullable final ProjectDTO selectedProject = serviceLocator.getSelectedProject();
        if (selectedProject == null) throw new NoSelectedProjectException();

        terminalService.println(getTitle() + " for " + selectedProject.getName());
        @NotNull final List<TaskDTO> taskList = projectEndpoint.getProjectTasks(serviceLocator.getCurrentSession(), selectedProject);

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
            terminalService.println("\tStatus: " + task.getStatus());
        }
    }

}
