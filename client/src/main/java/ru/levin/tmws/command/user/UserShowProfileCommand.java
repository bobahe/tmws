package ru.levin.tmws.command.user;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.ISessionEndpoint;
import ru.levin.tmws.api.endpoint.UserDTO;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;

public final class UserShowProfileCommand extends AbstractCommand {

    @NotNull
    private final ITerminalService terminalService;

    @NonNull
    private final ISessionEndpoint sessionEndpoint;

    public UserShowProfileCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
        this.terminalService = serviceLocator.getTerminalService();
        this.sessionEndpoint = serviceLocator.getSessionService().getSessionEndpointPort();
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
