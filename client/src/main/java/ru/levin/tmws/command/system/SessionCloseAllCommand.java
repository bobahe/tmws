package ru.levin.tmws.command.system;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.ISessionEndpoint;
import ru.levin.tmws.command.AbstractCommand;

@Component
public final class SessionCloseAllCommand extends AbstractCommand {

    @NonNull
    private ISessionEndpoint sessionEndpoint;
    @Autowired
    public void setSessionEndpoint(@NotNull final ISessionEndpoint sessionEndpoint) {
        this.sessionEndpoint = sessionEndpoint;
    }

    @Autowired
    public SessionCloseAllCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
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
