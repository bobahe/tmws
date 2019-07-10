package ru.levin.tmws.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.ISessionEndpoint;
import ru.levin.tmws.api.endpoint.IUserEndpoint;
import ru.levin.tmws.api.endpoint.User;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;
import ru.levin.tmws.exception.NoSuchItemException;

public final class UserEditProfileCommand extends AbstractCommand {

    @NotNull
    private final ITerminalService terminalService;

    @NotNull
    private final IUserEndpoint userEndpoint;

    @NotNull
    private final ISessionEndpoint sessionEndpoint;

    public UserEditProfileCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
        this.terminalService = serviceLocator.getTerminalService();
        this.userEndpoint = serviceLocator.getUserService().getUserEndpointPort();
        this.sessionEndpoint = serviceLocator.getSessionService().getSessionEndpointPort();
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
        @Nullable final User currentUser = sessionEndpoint.getUser(serviceLocator.getCurrentSession());
        if (currentUser == null) throw new NoSuchItemException();
        terminalService.println("Enter login (saved: " + currentUser.getLogin() + "):");
        currentUser.setLogin(terminalService.getLine());
        terminalService.println("Enter first name (saved: " + currentUser.getFirstName() + "):");
        currentUser.setFirstName(terminalService.getLine());
        terminalService.println("Enter last name (saved: " + currentUser.getLastName() + "):");
        currentUser.setLastName(terminalService.getLine());
        terminalService.println("Enter middle name (saved: " + currentUser.getMiddleName() + "):");
        currentUser.setMiddleName(terminalService.getLine());
        terminalService.println("Enter email (saved: " + currentUser.getEmail() + "):");
        currentUser.setEmail(terminalService.getLine());
        terminalService.println(("Enter phone (saved: " + currentUser.getPhone() + "):"));
        currentUser.setPhone(terminalService.getLine());
        userEndpoint.changeProfile(serviceLocator.getCurrentSession(), currentUser);
    }

}
