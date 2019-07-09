package ru.levin.tmws.client.command.project;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.client.api.IServiceLocator;
import ru.levin.tmws.client.api.service.ITerminalService;
import ru.levin.tmws.client.command.AbstractCommand;
import ru.levin.tmws.client.exception.NoSuchItemException;
import ru.levin.tmws.server.api.endpoint.IProjectEndpoint;

public final class ProjectSelectCommand extends AbstractCommand {

    @NotNull
    private static final String SELECTED_PROJECT_MESSAGE = "SELECTED PROJECT: ";

    @NotNull
    private static final String SERIAL_NUMBER_PROMPT = "ENTER SERIAL NUMBER:";

    @NotNull
    private final ITerminalService terminalService;

    @NotNull
    private final IProjectEndpoint projectEndpoint;

    public ProjectSelectCommand(@NotNull final IServiceLocator bootstrap) {
        super(bootstrap);
        this.terminalService = bootstrap.getTerminalService();
        this.projectEndpoint = bootstrap.getProjectService().getProjectEndpointPort();
    }

    @Override
    @NotNull
    public String getName() {
        return "project-select";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "[PROJECT SELECT]";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Select project";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() {
        terminalService.println(this.getTitle());
        terminalService.println(SERIAL_NUMBER_PROMPT);
        final int index = Integer.parseInt(terminalService.getLine());
        bootstrap.setSelectedProject(projectEndpoint.getProjectById(bootstrap.getCurrentSession(), index));
        if (bootstrap.getSelectedProject() == null) throw new NoSuchItemException();
        terminalService.println(SELECTED_PROJECT_MESSAGE + bootstrap.getSelectedProject().getName());
    }

}
