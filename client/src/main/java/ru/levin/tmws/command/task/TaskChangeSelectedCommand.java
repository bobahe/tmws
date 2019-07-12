package ru.levin.tmws.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.ITaskEndpoint;
import ru.levin.tmws.api.endpoint.Status;
import ru.levin.tmws.api.endpoint.TaskDTO;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;
import ru.levin.tmws.exception.NoSelectedTaskException;
import ru.levin.tmws.util.CommandUtil;

import javax.xml.datatype.DatatypeFactory;
import java.util.Date;

public final class TaskChangeSelectedCommand extends AbstractCommand {

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
    private final ITaskEndpoint taskEndpoint;

    public TaskChangeSelectedCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
        this.terminalService = serviceLocator.getTerminalService();
        this.taskEndpoint = serviceLocator.getTaskService().getTaskEndpointPort();
    }

    @Override
    @NotNull
    public String getName() {
        return "task-change";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "[CHANGE TASK]";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Change selected task";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() throws Exception {
        if (serviceLocator.getSelectedTask() == null) throw new NoSelectedTaskException();
        terminalService.println(getTitle());
        @NotNull final TaskDTO task = serviceLocator.getSelectedTask();
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
        terminalService.println(STATUS_PROMPT);
        @NotNull final Status status = Status.valueOf(terminalService.getLine());
        task.setStatus(status);
        taskEndpoint.updateTask(serviceLocator.getCurrentSession(), task);
    }

}
