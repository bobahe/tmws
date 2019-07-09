package ru.levin.tmws.context;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.Project;
import ru.levin.tmws.api.endpoint.Session;
import ru.levin.tmws.api.endpoint.Task;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;
import ru.levin.tmws.endpoint.*;
import ru.levin.tmws.exception.CommandNotFoundException;
import ru.levin.tmws.service.TerminalService;

import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;
import java.util.Map;

@NoArgsConstructor
public final class Bootstrap implements IServiceLocator {

    @NotNull
    @Getter
    private final Map<String, AbstractCommand> commands = new LinkedHashMap<>();

    @NotNull
    @Getter
    private final ITerminalService terminalService = new TerminalService();

    @NotNull
    @Getter
    private final AdminEndpointService adminService = new AdminEndpointService();

    @NotNull
    @Getter
    private UserEndpointService userService = new UserEndpointService();

    @NotNull
    @Getter
    private ProjectEndpointService projectService = new ProjectEndpointService();

    @NotNull
    @Getter
    private TaskEndpointService taskService = new TaskEndpointService();

    @NotNull
    @Getter
    private SessionEndpointService sessionService = new SessionEndpointService();

    @Nullable
    @Getter
    @Setter
    private Session currentSession;

    @Nullable
    @Getter
    @Setter
    private Project selectedProject;

    @Nullable
    @Getter
    @Setter
    private Task selectedTask;

    public void init(@NotNull final Class[] commands) {
        registerCommands(commands);
        process();
    }

    private void registerCommands(@NotNull final Class[] commands) {
        for (final Class<?> cmdClass : commands) {
            if (cmdClass.getSuperclass().equals(AbstractCommand.class)) {
                try {
                    @NotNull final Constructor<?> constructor = cmdClass.getConstructor(IServiceLocator.class);
                    AbstractCommand command =
                            ((AbstractCommand) constructor.newInstance(this));
                    this.commands.put(command.getName(), command);
                } catch (Exception e) {
                    terminalService.printerr(e.getMessage());
                }
            }
        }
    }

    private void process() {
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
