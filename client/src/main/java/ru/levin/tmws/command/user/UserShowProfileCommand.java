package ru.levin.tmws.command.user;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.ISessionEndpoint;
import ru.levin.tmws.api.endpoint.UserDTO;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;

@Component
public final class UserShowProfileCommand extends AbstractCommand {

    @NotNull
    private ITerminalService terminalService;
    @Autowired
    public void setTerminalService(@NotNull final ITerminalService terminalService) {
        this.terminalService = terminalService;
    }

    @NonNull
    private ISessionEndpoint sessionEndpoint;
    @Autowired
    public void setSessionEndpoint(@NotNull final ISessionEndpoint sessionEndpoint) {
        this.sessionEndpoint = sessionEndpoint;
    }

    @Autowired
    public UserShowProfileCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
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
        @NotNull final UserDTO user = sessionEndpoint.getUser(serviceLocator.getCurrentSession());
        terminalService.println("Id: " + user.getId());
        terminalService.println("Login: " + user.getLogin());
        terminalService.println("Role: " + user.getRoleType().value());
        terminalService.println("Full name: " + user.getLastName() + " " + user.getFirstName() + " " + user.getMiddleName());
        terminalService.println("Email: " + user.getEmail());
        terminalService.println("Phone: " + user.getPhone());
    }

}
