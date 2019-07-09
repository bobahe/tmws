package ru.levin.tmws.client.command.project;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.client.api.IServiceLocator;
import ru.levin.tmws.client.api.service.ITerminalService;
import ru.levin.tmws.client.command.AbstractCommand;
import ru.levin.tmws.client.exception.NoStatusException;
import ru.levin.tmws.server.api.endpoint.IProjectEndpoint;
import ru.levin.tmws.server.api.endpoint.Project;

import java.util.List;

public final class ProjectFindCommand extends AbstractCommand {

    @NotNull
    private final ITerminalService terminalService;

    @NotNull
    private final IProjectEndpoint projectEndpoint;

    public ProjectFindCommand(@NotNull final IServiceLocator bootstrap) {
        super(bootstrap);
        this.terminalService = bootstrap.getTerminalService();
        this.projectEndpoint = bootstrap.getProjectService().getProjectEndpointPort();
    }

    @Override
    @NotNull
    public String getName() {
        return "project-find";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "[PROJECT FIND]";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Find project by name or description";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() {
        terminalService.println(getTitle());
        terminalService.println("Enter name or description to find: ");
        @NotNull final String matcher = terminalService.getLine();
        @NotNull List<Project> projectList = projectEndpoint.getProjectByNameOrDescription(bootstrap.getCurrentSession(), matcher);
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
