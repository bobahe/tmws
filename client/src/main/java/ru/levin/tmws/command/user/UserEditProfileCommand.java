package ru.levin.tmws.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.ISessionEndpoint;
import ru.levin.tmws.api.endpoint.IUserEndpoint;
import ru.levin.tmws.api.endpoint.UserDTO;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;
import ru.levin.tmws.exception.NoSuchItemException;

@Component
public final class UserEditProfileCommand extends AbstractCommand {

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

    @NotNull
    private ISessionEndpoint sessionEndpoint;
    @Autowired
    public void setSessionEndpoint(@NotNull final ISessionEndpoint sessionEndpoint) {
        this.sessionEndpoint = sessionEndpoint;
    }

    @Autowired
    public UserEditProfileCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
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
        @Nullable final UserDTO currentUser = sessionEndpoint.getUser(serviceLocator.getCurrentSession());
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
