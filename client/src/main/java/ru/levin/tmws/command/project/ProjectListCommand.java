package ru.levin.tmws.command.project;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.IProjectEndpoint;
import ru.levin.tmws.api.endpoint.Project;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;
import ru.levin.tmws.exception.NoStatusException;
import ru.levin.tmws.util.CommandUtil;

import java.util.List;

public final class ProjectListCommand extends AbstractCommand {

    @NotNull
    private final ITerminalService terminalService;

    @NotNull
    private final IProjectEndpoint projectEndpoint;

    public ProjectListCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
        this.terminalService = serviceLocator.getTerminalService();
        this.projectEndpoint = serviceLocator.getProjectService().getProjectEndpointPort();
    }

    @Override
    @NotNull
    public String getName() {
        return "project-list";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "[PROJECT LIST]";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Show all projects";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() {
        @NotNull final List<Project> projectList = projectEndpoint.getProjectAll(serviceLocator.getCurrentSession());
        terminalService.println("Select option to sortTaskList list (1 by default):");
        terminalService.println("1. Saved order");
        terminalService.println("2. Start date");
        terminalService.println("3. End date");
        terminalService.println("4. Status");
        @NotNull final String orderType = terminalService.getLine();
        CommandUtil.sortProjectList(orderType, projectList);
        for (int i = 0; i < projectList.size(); i++) {
            @NotNull final Project project = projectList.get(i);
            if (project.getStatus() == null) throw new NoStatusException();
            terminalService.println((i + 1) + ". " + project.getName());
            terminalService.println("\tDescription: " + project.getDescription());
            if (project.getStartDate() != null) {
                terminalService.println("\tStart date: " + project.getStartDate());
            } else {
                terminalService.println("\tStar date: not set");
            }
            if (project.getEndDate() != null) {
                terminalService.println("\tEnd date: " + project.getEndDate());
            } else {
                terminalService.println("\tEnd date: not set");
            }
            terminalService.println("\tStatus: " + project.getStatus().value());
        }
    }

}
