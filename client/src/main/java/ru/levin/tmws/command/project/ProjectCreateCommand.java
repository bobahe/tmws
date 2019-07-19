package ru.levin.tmws.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.IProjectEndpoint;
import ru.levin.tmws.api.endpoint.ProjectDTO;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;
import ru.levin.tmws.util.CommandUtil;

import javax.xml.datatype.DatatypeFactory;
import java.util.Date;

@Component
public final class ProjectCreateCommand extends AbstractCommand {

    @NotNull
    protected static final String NAME_PROMPT = "ENTER NAME:";

    @NotNull
    protected static final String DESCRIPTION_PROMPT = "ENTER DESCRIPTION:";

    @NotNull
    protected static final String START_DATE_PROMPT = "ENTER START DATE:";

    @NotNull
    protected static final String END_DATE_PROMPT = "ENTER END DATE:";

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
    public ProjectCreateCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    @NotNull
    public String getName() {
        return "project-create";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "[PROJECT CREATE]";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Create new project";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() throws Exception {
        terminalService.println(getTitle());
        @NotNull final ProjectDTO project = new ProjectDTO();
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
        projectEndpoint.createProject(serviceLocator.getCurrentSession(), project);
    }

}
