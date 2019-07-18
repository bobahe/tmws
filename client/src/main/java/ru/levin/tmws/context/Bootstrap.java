package ru.levin.tmws.context;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.ProjectDTO;
import ru.levin.tmws.api.endpoint.SessionDTO;
import ru.levin.tmws.api.endpoint.TaskDTO;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;
import ru.levin.tmws.command.persist.*;
import ru.levin.tmws.command.project.*;
import ru.levin.tmws.command.system.AboutCommand;
import ru.levin.tmws.command.system.HelpCommand;
import ru.levin.tmws.command.system.ServerInfoCommand;
import ru.levin.tmws.command.system.SessionCloseAllCommand;
import ru.levin.tmws.command.task.*;
import ru.levin.tmws.command.user.*;
import ru.levin.tmws.endpoint.*;
import ru.levin.tmws.exception.CommandNotFoundException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;
import java.util.Map;

@ApplicationScoped
public class Bootstrap implements IServiceLocator {

    @NotNull
    @Getter
    private final Map<String, AbstractCommand> commands = new LinkedHashMap<>();

    @Nullable
    @Getter
    @Inject
    private ITerminalService terminalService;

    @Nullable
    @Getter
    @Inject
    private AdminEndpointService adminService;

    @Nullable
    @Getter
    @Inject
    private ProjectEndpointService projectService;

    @Nullable
    @Getter
    @Inject
    private SessionEndpointService sessionService;

    @Nullable
    @Getter
    @Inject
    private TaskEndpointService taskService;

    @Nullable
    @Getter
    @Inject
    private UserEndpointService userService;

    @Nullable
    @Getter
    @Inject
    private ServerEndpointService serverService;

    @Nullable
    @Getter
    @Setter
    private SessionDTO currentSession;

    @Nullable
    @Getter
    @Setter
    private ProjectDTO selectedProject;

    @Nullable
    @Getter
    @Setter
    private TaskDTO selectedTask;

    @NotNull
    private static final Class[] COMMANDS = {
            HelpCommand.class, UserLoginCommand.class, UserRegisterCommand.class,
            AboutCommand.class, ProjectListCommand.class, ProjectCreateCommand.class,
            ProjectFindCommand.class, ProjectSelectCommand.class, ProjectRemoveAllCommand.class,
            ProjectChangeSelectedCommand.class, ProjectRemoveSelectedCommand.class, TaskProjectTaskListCommand.class,
            TaskRemoveAllCommand.class, TaskCreateCommand.class, TaskFindCommand.class, TaskSelectCommand.class,
            TaskListCommand.class, TaskChangeSelectedCommand.class, TaskRemoveSelectedCommand.class,
            TaskJoinCommand.class, UserLogoutCommand.class, UserChangePasswordCommand.class,
            UserShowProfileCommand.class, UserEditProfileCommand.class, SerializedSaveCommand.class,
            SerializedLoadCommand.class, JAXBXmlSaveCommand.class, JAXBXmlLoadCommand.class,
            JAXBJsonSaveCommand.class, JAXBJsonLoadCommand.class, FasterXmlSaveCommand.class,
            FasterXmlLoadCommand.class, FasterJsonSaveCommand.class, FasterJsonLoadCommand.class,
            SessionCloseAllCommand.class, ServerInfoCommand.class
    };

    public void init() {
        registerCommands();
        process();
    }

    private void registerCommands() {
        if (terminalService == null) return;
        for (final Class<?> cmdClass : COMMANDS) {
            if (cmdClass.getSuperclass().equals(AbstractCommand.class)) {
                try {
                    Constructor<?> constructor = cmdClass.getConstructor(IServiceLocator.class);
                    AbstractCommand command = (AbstractCommand) constructor.newInstance(this);
                    this.commands.put(command.getName(), command);
                } catch (Exception e) {
                    terminalService.printerr(e.getMessage());
                }
            }
        }
    }

    private void process() {
        if (terminalService == null) return;
        terminalService.println("*** WELCOME TO TASK MANAGER ***");
        @NotNull String command = terminalService.getLine();

        while (!"exit".equals(command)) {
            if (command.isEmpty()) {
                command = terminalService.getLine();
                continue;
            }
            invokeCommand(command);
            command = terminalService.getLine();
        }
    }

    private void invokeCommand(String commandName) {
        if (terminalService == null) return;
        @Nullable final AbstractCommand command = commands.get(commandName);
        try {
            if (command == null) throw new CommandNotFoundException();
            command.execute();
            terminalService.println("[OK]");
        } catch (Exception e) {
            terminalService.printerr(e.getMessage());
        }
    }

}
