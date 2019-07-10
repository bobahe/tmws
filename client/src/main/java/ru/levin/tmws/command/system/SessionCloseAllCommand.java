package ru.levin.tmws.command.system;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.ISessionEndpoint;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;

public final class SessionCloseAllCommand extends AbstractCommand {

    @NotNull
    private final ITerminalService terminalService;

    @NonNull
    private final ISessionEndpoint sessionEndpoint;

    public SessionCloseAllCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
        this.terminalService = serviceLocator.getTerminalService();
        this.sessionEndpoint = serviceLocator.getSessionService().getSessionEndpointPort();
    }

    @Override
    @NotNull
    public String getName() {
        return "session-close-all";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Close all sessions";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() {
        sessionEndpoint.closeSessionAll(serviceLocator.getCurrentSession());
    }

}
