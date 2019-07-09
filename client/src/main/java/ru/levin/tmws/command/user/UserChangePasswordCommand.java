package ru.levin.tmws.command.user;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.IUserEndpoint;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;
import ru.levin.tmws.exception.PasswordsNotEqualException;

public final class UserChangePasswordCommand extends AbstractCommand {

    @NotNull
    protected static final String PASSWORD_PROMPT = "ENTER PASSWORD:";

    @NotNull
    protected static final String PASSWORD_AGAIN_PROMPT = "ENTER PASSWORD AGAIN:";

    @NotNull
    private final ITerminalService terminalService;

    @NotNull
    private final IUserEndpoint userEndpoint;

    public UserChangePasswordCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
        this.terminalService = serviceLocator.getTerminalService();
        this.userEndpoint = serviceLocator.getUserService().getUserEndpointPort();
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
        userEndpoint.changeUserPassword(serviceLocator.getCurrentSession(), passwordFirst);
    }

}
