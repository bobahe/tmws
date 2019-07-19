package ru.levin.tmws.command.project;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.IProjectEndpoint;
import ru.levin.tmws.api.endpoint.ProjectDTO;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;
import ru.levin.tmws.exception.NoStatusException;

import java.util.List;

@Component
public final class ProjectFindCommand extends AbstractCommand {

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
    public ProjectFindCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
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
        @NotNull List<ProjectDTO> projectList =
                projectEndpoint.getProjectByNameOrDescription(serviceLocator.getCurrentSession(), matcher);
        for (int i = 0; i < projectList.size(); i++) {
            @NotNull final ProjectDTO project = projectList.get(i);
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
