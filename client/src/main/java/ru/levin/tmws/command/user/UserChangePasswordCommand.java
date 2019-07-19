package ru.levin.tmws.command.user;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.IUserEndpoint;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;
import ru.levin.tmws.exception.PasswordsNotEqualException;

@Component
public final class UserChangePasswordCommand extends AbstractCommand {

    @NotNull
    protected static final String PASSWORD_PROMPT = "ENTER PASSWORD:";

    @NotNull
    protected static final String PASSWORD_AGAIN_PROMPT = "ENTER PASSWORD AGAIN:";

    @NotNull
    private ITerminalService terminalService;
    @Autowired
    public void setTerminalService(@NotNull final ITerminalService terminalService) {
        this.terminalService = terminalService;
    }

    @NotNull
    private IUserEndpoint userEndpoint;
    @Autowired
    public void setUserEndpoint(@NotNull final IUserEndpoint userEndpoint) {
        this.userEndpoint = userEndpoint;
    }

    @Autowired
    public UserChangePasswordCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
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
