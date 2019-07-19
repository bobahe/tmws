package ru.levin.tmws.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.ITaskEndpoint;
import ru.levin.tmws.api.endpoint.TaskDTO;
import ru.levin.tmws.command.AbstractCommand;
import ru.levin.tmws.exception.NoSelectedTaskException;

@Component
public final class TaskRemoveSelectedCommand extends AbstractCommand {

    @NotNull
    private ITaskEndpoint taskEndpoint;
    @Autowired
    public void setTaskEndpoint(@NotNull final ITaskEndpoint taskEndpoint) {
        this.taskEndpoint = taskEndpoint;
    }

    @Autowired
    public TaskRemoveSelectedCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    @NotNull
    public String getName() {
        return "task-remove";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Remove selected task";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() {
        @Nullable final TaskDTO selectedTask = serviceLocator.getSelectedTask();
        if (selectedTask == null) throw new NoSelectedTaskException();
        taskEndpoint.removeTask(serviceLocator.getCurrentSession(), selectedTask);
        serviceLocator.setSelectedTask(null);
    }

}
