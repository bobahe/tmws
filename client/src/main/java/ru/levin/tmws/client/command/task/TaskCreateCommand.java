package ru.levin.tmws.client.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.client.api.IServiceLocator;
import ru.levin.tmws.client.api.service.ITerminalService;
import ru.levin.tmws.client.command.AbstractCommand;
import ru.levin.tmws.client.util.CommandUtil;
import ru.levin.tmws.server.api.endpoint.ITaskEndpoint;
import ru.levin.tmws.server.api.endpoint.Project;
import ru.levin.tmws.server.api.endpoint.Task;

import javax.xml.datatype.DatatypeFactory;
import java.util.Date;

public final class TaskCreateCommand extends AbstractCommand {

    @NotNull
    protected static final String NAME_PROMPT = "ENTER NAME:";

    @NotNull
    protected static final String DESCRIPTION_PROMPT = "ENTER DESCRIPTION:";

    @NotNull
    protected static final String START_DATE_PROMPT = "ENTER START DATE:";

    @NotNull
    protected static final String END_DATE_PROMPT = "ENTER END DATE:";

    @NotNull
    private static final String JOIN_TO_PROJECT_PROMPT = "Would you like to attach this task to selected project? (Y/n)";

    @NotNull
    private final ITerminalService terminalService;

    @NotNull final ITaskEndpoint taskEndpoint;

    public TaskCreateCommand(@NotNull final IServiceLocator bootstrap) {
        super(bootstrap);
        this.terminalService = bootstrap.getTerminalService();
        this.taskEndpoint = bootstrap.getTaskService().getTaskEndpointPort();
    }

    @Override
    @NotNull
    public String getName() {
        return "task-create";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "[TASK CREATE]";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Create new task";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() throws Exception {
        terminalService.println(getTitle());
        @NotNull final Task task = new Task();
        terminalService.println(NAME_PROMPT);
        task.setName(terminalService.getLine());
        terminalService.println(DESCRIPTION_PROMPT);
        task.setDescription(terminalService.getLine());
        terminalService.println(START_DATE_PROMPT);
        @Nullable final Date startDate = CommandUtil.parseDate(terminalService.getLine());
        if (startDate != null) {
            task.setStartDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(startDate.toInstant().toString()));
        }
        terminalService.println(END_DATE_PROMPT);
        @Nullable final Date endDate = CommandUtil.parseDate(terminalService.getLine());
        if (endDate != null) {
            task.setEndDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(endDate.toInstant().toString()));
        }
        @Nullable final Project selectedProject = bootstrap.getSelectedProject();
        if (selectedProject != null) {
            terminalService.println(JOIN_TO_PROJECT_PROMPT);
            @NotNull final String joinAnswer = terminalService.getLine();
            if (!joinAnswer.equals("n")) {
                task.setProjectId(selectedProject.getId());
            }
        }
        taskEndpoint.createTask(bootstrap.getCurrentSession(), task);
    }

}
