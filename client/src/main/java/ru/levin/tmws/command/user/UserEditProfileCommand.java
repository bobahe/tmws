package ru.levin.tmws.command.user;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.IUserEndpoint;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;

public final class UserEditProfileCommand extends AbstractCommand {

    @NotNull
    protected static final String LOGIN_PROMPT = "ENTER LOGIN:";

    @NotNull
    private final ITerminalService terminalService;

    @NotNull
    private final IUserEndpoint userEndpoint;

    public UserEditProfileCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
        this.terminalService = serviceLocator.getTerminalService();
        this.userEndpoint = serviceLocator.getUserService().getUserEndpointPort();
    }

    @Override
    @NotNull
    public String getName() {
        return "edit-profile";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Edit user profile";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() {
        terminalService.println(LOGIN_PROMPT);
        @NotNull final String login = terminalService.getLine();
        userEndpoint.changeLogin(serviceLocator.getCurrentSession(), login);
    }

}
