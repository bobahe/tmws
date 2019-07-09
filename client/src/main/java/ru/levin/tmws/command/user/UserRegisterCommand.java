package ru.levin.tmws.command.user;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.IUserEndpoint;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;

public final class UserRegisterCommand extends AbstractCommand {

    @NotNull
    protected static final String LOGIN_PROMPT = "ENTER LOGIN:";

    @NotNull
    protected static final String PASSWORD_PROMPT = "ENTER PASSWORD:";

    @NotNull
    private final ITerminalService terminalService;

    @NotNull
    private final IUserEndpoint userEndpoint;

    public UserRegisterCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
        this.terminalService = serviceLocator.getTerminalService();
        this.userEndpoint = serviceLocator.getUserService().getUserEndpointPort();
    }

    @Override
    @NotNull
    public String getName() {
        return "register";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "[REGISTRATION]";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Register user in the application";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return false;
    }

    @Override
    public void execute() {
        terminalService.println(getTitle());
        terminalService.println(LOGIN_PROMPT);
        @NotNull final String login = terminalService.getLine();
        terminalService.println(PASSWORD_PROMPT);
        @NotNull final String password = terminalService.getLine();
        userEndpoint.registerUser(login, password);
    }

}
