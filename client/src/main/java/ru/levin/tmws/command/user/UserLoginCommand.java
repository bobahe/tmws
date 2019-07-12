package ru.levin.tmws.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.ISessionEndpoint;
import ru.levin.tmws.api.endpoint.SessionDTO;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;

public final class UserLoginCommand extends AbstractCommand {

    @NotNull
    protected static final String LOGIN_PROMPT = "ENTER LOGIN:";

    @NotNull
    protected static final String PASSWORD_PROMPT = "ENTER PASSWORD:";

    @NotNull
    private final ITerminalService terminalService;

    @NotNull
    private final ISessionEndpoint sessionEndpoint;

    public UserLoginCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
        this.terminalService = serviceLocator.getTerminalService();
        this.sessionEndpoint = serviceLocator.getSessionService().getSessionEndpointPort();
    }

    @Override
    @NotNull
    public String getName() {
        return "login";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Log in to application";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return false;
    }

    @Override
    public void execute() {
        terminalService.println(LOGIN_PROMPT);
        @NotNull final String login = terminalService.getLine();
        terminalService.println(PASSWORD_PROMPT);
        @NotNull final String password = terminalService.getLine();
        @Nullable SessionDTO session = sessionEndpoint.openSession(login, password);
        serviceLocator.setCurrentSession(session);
    }

}
