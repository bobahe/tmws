package ru.levin.tmws.client.command.user;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.client.api.IServiceLocator;
import ru.levin.tmws.client.api.service.ITerminalService;
import ru.levin.tmws.client.command.AbstractCommand;
import ru.levin.tmws.client.exception.PasswordsNotEqualException;
import ru.levin.tmws.server.api.endpoint.IUserEndpoint;

public final class UserChangePasswordCommand extends AbstractCommand {

    @NotNull
    protected static final String PASSWORD_PROMPT = "ENTER PASSWORD:";

    @NotNull
    protected static final String PASSWORD_AGAIN_PROMPT = "ENTER PASSWORD AGAIN:";

    @NotNull
    private final ITerminalService terminalService;

    @NotNull
    private final IUserEndpoint userEndpoint;

    public UserChangePasswordCommand(@NotNull final IServiceLocator bootstrap) {
        super(bootstrap);
        this.terminalService = bootstrap.getTerminalService();
        this.userEndpoint = bootstrap.getUserService().getUserEndpointPort();
    }

    @Override
    @NotNull
    public String getName() {
        return "change-password";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Change password";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() {
        terminalService.println(PASSWORD_PROMPT);
        @NotNull final String passwordFirst = terminalService.getLine();
        terminalService.println(PASSWORD_AGAIN_PROMPT);
        @NotNull final String passwordSecond = terminalService.getLine();
        if (!passwordFirst.equals(passwordSecond)) throw new PasswordsNotEqualException();
        userEndpoint.changeUserPassword(bootstrap.getCurrentSession(), passwordFirst);
    }

}
