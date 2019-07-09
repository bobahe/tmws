package ru.levin.tmws.command.project;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.IProjectEndpoint;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;
import ru.levin.tmws.exception.NoSuchItemException;

public final class ProjectSelectCommand extends AbstractCommand {

    @NotNull
    private static final String SELECTED_PROJECT_MESSAGE = "SELECTED PROJECT: ";

    @NotNull
    private static final String SERIAL_NUMBER_PROMPT = "ENTER SERIAL NUMBER:";

    @NotNull
    private final ITerminalService terminalService;

    @NotNull
    private final IProjectEndpoint projectEndpoint;

    public ProjectSelectCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
        this.terminalService = serviceLocator.getTerminalService();
        this.projectEndpoint = serviceLocator.getProjectService().getProjectEndpointPort();
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
        serviceLocator.setSelectedProject(projectEndpoint.getProjectById(serviceLocator.getCurrentSession(), index));
        if (serviceLocator.getSelectedProject() == null) throw new NoSuchItemException();
        terminalService.println(SELECTED_PROJECT_MESSAGE + serviceLocator.getSelectedProject().getName());
    }

}
