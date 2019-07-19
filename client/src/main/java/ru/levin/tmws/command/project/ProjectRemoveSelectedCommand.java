package ru.levin.tmws.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.IProjectEndpoint;
import ru.levin.tmws.api.endpoint.ProjectDTO;
import ru.levin.tmws.command.AbstractCommand;
import ru.levin.tmws.exception.NoSelectedTaskException;

@Component
public class ProjectRemoveSelectedCommand extends AbstractCommand {

    @NotNull
    private IProjectEndpoint projectEndpoint;
    @Autowired
    public void setProjectEndpoint(@NotNull final IProjectEndpoint projectEndpoint) {
        this.projectEndpoint = projectEndpoint;
    }

    @Autowired
    public ProjectRemoveSelectedCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    @NotNull
    public String getName() {
        return "project-remove";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Remove selected project";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() {
        @Nullable final ProjectDTO selectedProject = serviceLocator.getSelectedProject();
        if (selectedProject == null) throw new NoSelectedTaskException();
        projectEndpoint.removeProject(serviceLocator.getCurrentSession(), selectedProject);
        serviceLocator.setSelectedProject(null);
    }

}
