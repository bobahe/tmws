package ru.levin.tmws.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.ITaskEndpoint;
import ru.levin.tmws.api.endpoint.ProjectDTO;
import ru.levin.tmws.api.endpoint.TaskDTO;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;
import ru.levin.tmws.exception.NoSelectedProjectException;
import ru.levin.tmws.exception.NoSelectedTaskException;

@Component
public final class TaskJoinCommand extends AbstractCommand {

    @NotNull
    private static final String SELECTED_PROJECT_MESSAGE = "SELECTED PROJECT: ";

    @NotNull
    private static final String SELECTED_TASK_MESSAGE = "SELECTED TASK: ";

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
    public TaskJoinCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
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
        @Nullable final ProjectDTO selectedProject = serviceLocator.getSelectedProject();
        @Nullable final TaskDTO selectedTask = serviceLocator.getSelectedTask();
        if (selectedProject == null || selectedProject.getId() == null) throw new NoSelectedProjectException();
        if (selectedTask == null) throw new NoSelectedTaskException();

        terminalService.println(this.getTitle());
        terminalService.println(SELECTED_PROJECT_MESSAGE + selectedProject);
        terminalService.println(SELECTED_TASK_MESSAGE + selectedTask);
        terminalService.println(getDescription() + "? (Y/n)");
        @NotNull final String joinAnswer = terminalService.getLine();
        if (!"n".equals(joinAnswer)) selectedTask.setProjectId(selectedProject.getId());

        taskEndpoint.updateTask(serviceLocator.getCurrentSession(), selectedTask);
    }

}
