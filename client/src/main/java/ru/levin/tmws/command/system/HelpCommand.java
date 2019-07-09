package ru.levin.tmws.command.system;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;

import java.util.Map;

public final class HelpCommand extends AbstractCommand {

    @NotNull
    private final Map<String, AbstractCommand> commands;

    @NotNull
    private final ITerminalService terminalService;

    public HelpCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
        this.commands = serviceLocator.getCommands();
        this.terminalService = serviceLocator.getTerminalService();
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
