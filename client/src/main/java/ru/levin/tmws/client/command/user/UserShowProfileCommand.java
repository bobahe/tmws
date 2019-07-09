package ru.levin.tmws.client.command.user;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.client.api.IServiceLocator;
import ru.levin.tmws.client.api.service.ITerminalService;
import ru.levin.tmws.client.command.AbstractCommand;
import ru.levin.tmws.server.api.endpoint.ISessionEndpoint;
import ru.levin.tmws.server.api.endpoint.User;

public final class UserShowProfileCommand extends AbstractCommand {

    @NotNull
    private final ITerminalService terminalService;

    @NonNull
    private final ISessionEndpoint sessionEndpoint;

    public UserShowProfileCommand(@NotNull final IServiceLocator bootstrap) {
        super(bootstrap);
        this.terminalService = bootstrap.getTerminalService();
        this.sessionEndpoint = bootstrap.getSessionService().getSessionEndpointPort();
    }

    @Override
    @NotNull
    public String getName() {
        return "show-profile";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Shows user profile";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() {
        @NotNull final User user = sessionEndpoint.getUser(bootstrap.getCurrentSession());
        terminalService.println("Id: " + user.getId());
        terminalService.println("Login: " + user.getLogin());
        terminalService.println("Role: " + user.getRoleType().value());
    }

}
