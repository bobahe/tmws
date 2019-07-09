package ru.levin.tmws.client.command.system;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.client.api.IServiceLocator;
import ru.levin.tmws.client.api.service.ITerminalService;
import ru.levin.tmws.client.command.AbstractCommand;

import java.util.Map;

public final class HelpCommand extends AbstractCommand {

    @NotNull
    private final Map<String, AbstractCommand> commands;

    @NotNull
    private final ITerminalService terminalService;

    public HelpCommand(@NotNull final IServiceLocator bootstrap) {
        super(bootstrap);
        this.commands = bootstrap.getCommands();
        this.terminalService = bootstrap.getTerminalService();
    }

    @Override
    @NotNull
    public String getName() {
        return "help";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Show all commands";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return false;
    }

    @Override
    public void execute() {
        commands.values().forEach(command -> terminalService.println(command.getName() + ": " + command.getDescription()));
        terminalService.println("exit: Exit from application");
    }

}
