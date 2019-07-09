package ru.levin.tmws.client.command.user;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.client.api.IServiceLocator;
import ru.levin.tmws.client.api.service.ITerminalService;
import ru.levin.tmws.client.command.AbstractCommand;
import ru.levin.tmws.server.api.endpoint.ISessionEndpoint;

public final class UserLogoutCommand extends AbstractCommand {

    @NotNull
    private final ITerminalService terminalService;

    @NonNull
    private final ISessionEndpoint sessionEndpoint;

    public UserLogoutCommand(@NotNull final IServiceLocator bootstrap) {
        super(bootstrap);
        this.terminalService = bootstrap.getTerminalService();
        this.sessionEndpoint = bootstrap.getSessionService().getSessionEndpointPort();
    }

    @Override
    @NotNull
    public String getName() {
        return "logout";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Log out";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() {
        sessionEndpoint.closeSession(bootstrap.getCurrentSession());
    }

}
