package ru.levin.tmws.command.user;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.ISessionEndpoint;
import ru.levin.tmws.command.AbstractCommand;

@Component
public final class UserLogoutCommand extends AbstractCommand {

    @NonNull
    private ISessionEndpoint sessionEndpoint;
    @Autowired
    public void setSessionEndpoint(@NotNull final ISessionEndpoint sessionEndpoint) {
        this.sessionEndpoint = sessionEndpoint;
    }

    @Autowired
    public UserLogoutCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
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
        sessionEndpoint.closeSession(serviceLocator.getCurrentSession());
        serviceLocator.setCurrentSession(null);
    }

}
