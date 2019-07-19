package ru.levin.tmws.command.project;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.IProjectEndpoint;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;

@Component
public final class ProjectRemoveAllCommand extends AbstractCommand {

    @NotNull
    private static final String ALL_PROJECTS_REMOVED_MESSAGE = "[ALL PROJECTS REMOVED]\n";

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
    public ProjectRemoveAllCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    @NotNull
    public String getName() {
        return "project-clear";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Remove all projects";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() {
        projectEndpoint.removeProjectAll(serviceLocator.getCurrentSession());
        serviceLocator.setSelectedProject(null);
        terminalService.println(ALL_PROJECTS_REMOVED_MESSAGE);
    }

}
