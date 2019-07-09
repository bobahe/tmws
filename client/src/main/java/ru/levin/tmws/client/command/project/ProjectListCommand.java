package ru.levin.tmws.client.command.project;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.client.api.IServiceLocator;
import ru.levin.tmws.client.api.service.ITerminalService;
import ru.levin.tmws.client.command.AbstractCommand;
import ru.levin.tmws.client.exception.NoStatusException;
import ru.levin.tmws.client.util.CommandUtil;
import ru.levin.tmws.server.api.endpoint.IProjectEndpoint;
import ru.levin.tmws.server.api.endpoint.Project;
import ru.levin.tmws.server.api.endpoint.Task;

import java.util.List;

public final class ProjectListCommand extends AbstractCommand {

    @NotNull
    private final ITerminalService terminalService;

    @NotNull
    private final IProjectEndpoint projectEndpoint;

    public ProjectListCommand(@NotNull final IServiceLocator bootstrap) {
        super(bootstrap);
        this.terminalService = bootstrap.getTerminalService();
        this.projectEndpoint = bootstrap.getProjectService().getProjectEndpointPort();
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
        @NotNull final List<Project> projectList = projectEndpoint.getProjectAll(bootstrap.getCurrentSession());
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
