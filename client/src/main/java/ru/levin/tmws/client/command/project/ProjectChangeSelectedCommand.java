package ru.levin.tmws.client.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.client.api.IServiceLocator;
import ru.levin.tmws.client.api.service.ITerminalService;
import ru.levin.tmws.client.command.AbstractCommand;
import ru.levin.tmws.client.exception.NoSelectedProjectException;
import ru.levin.tmws.client.exception.NoSelectedTaskException;
import ru.levin.tmws.client.util.CommandUtil;
import ru.levin.tmws.server.api.endpoint.IProjectEndpoint;
import ru.levin.tmws.server.api.endpoint.Project;
import ru.levin.tmws.server.api.endpoint.Status;
import ru.levin.tmws.server.api.endpoint.Task;

import javax.xml.datatype.DatatypeFactory;
import java.util.Date;

public final class ProjectChangeSelectedCommand extends AbstractCommand {

    @NotNull
    protected static final String NAME_PROMPT = "ENTER NAME:";

    @NotNull
    protected static final String DESCRIPTION_PROMPT = "ENTER DESCRIPTION:";

    @NotNull
    protected static final String START_DATE_PROMPT = "ENTER START DATE:";

    @NotNull
    protected static final String END_DATE_PROMPT = "ENTER END DATE:";

    @NotNull
    protected static final String STATUS_PROMPT = "ENTER STATUS:";

    @NotNull
    private final ITerminalService terminalService;

    @NotNull
    private final IProjectEndpoint projectEndpoint;

    public ProjectChangeSelectedCommand(@NotNull final IServiceLocator bootstrap) {
        super(bootstrap);
        this.terminalService = bootstrap.getTerminalService();
        this.projectEndpoint = bootstrap.getProjectService().getProjectEndpointPort();
    }

    @Override
    @NotNull
    public String getName() {
        return "project-change";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "[CHANGE PROJECT]";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Change selected project";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() throws Exception {
        if (bootstrap.getSelectedProject() == null) throw new NoSelectedProjectException();
        terminalService.println(getTitle());
        @NotNull final Project project = bootstrap.getSelectedProject();
        terminalService.println(NAME_PROMPT);
        project.setName(terminalService.getLine());
        terminalService.println(DESCRIPTION_PROMPT);
        project.setDescription(terminalService.getLine());
        terminalService.println(START_DATE_PROMPT);
        @Nullable final Date startDate = CommandUtil.parseDate(terminalService.getLine());
        if (startDate != null) {
            project.setStartDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(startDate.toInstant().toString()));
        }
        terminalService.println(END_DATE_PROMPT);
        @Nullable final Date endDate = CommandUtil.parseDate(terminalService.getLine());
        if (endDate != null) {
            project.setEndDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(endDate.toInstant().toString()));
        }
        terminalService.println(STATUS_PROMPT);
        @NotNull final Status status = Status.valueOf(terminalService.getLine());
        project.setStatus(status);
        projectEndpoint.updateProject(bootstrap.getCurrentSession(), project);
    }

}
