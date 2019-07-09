package ru.levin.tmws.command.project;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.IProjectEndpoint;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;

public final class ProjectRemoveAllCommand extends AbstractCommand {

    @NotNull
    private static final String ALL_PROJECTS_REMOVED_MESSAGE = "[ALL PROJECTS REMOVED]\n";

    @NotNull
    private final ITerminalService terminalService;

    @NotNull
    private final IProjectEndpoint projectEndpoint;

    public ProjectRemoveAllCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
        this.terminalService = serviceLocator.getTerminalService();
        this.projectEndpoint = serviceLocator.getProjectService().getProjectEndpointPort();
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
