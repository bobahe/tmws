package ru.levin.tmws.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levin.tmws.api.endpoint.IUserEndpoint;
import ru.levin.tmws.api.service.ISessionService;
import ru.levin.tmws.api.service.IUserService;
import ru.levin.tmws.dto.SessionDTO;
import ru.levin.tmws.dto.UserDTO;
import ru.levin.tmws.entity.RoleType;
import ru.levin.tmws.exception.InternalServiceException;
import ru.levin.tmws.exception.SaveException;
import ru.levin.tmws.exception.SessionValidationException;
import ru.levin.tmws.util.ServiceUtil;

import javax.jws.WebService;

@Component
@WebService(endpointInterface = "ru.levin.tmws.api.endpoint.IUserEndpoint")
public class UserEndpoint implements IUserEndpoint {

    @NotNull
    private ISessionService sessionService;
    @Autowired
    public void setSessionService(@NotNull final ISessionService sessionService) { this.sessionService = sessionService; }

    @NotNull
    private IUserService userService;
    @Autowired
    public void setUserService(@NotNull final IUserService userService) { this.userService = userService; }

    @Override
    public void changeUserPassword(final @Nullable SessionDTO session, final @Nullable String password) {
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new SessionValidationException();
        if (password == null || password.isEmpty()) throw new SaveException();
        userService.setNewPassword(userService.findById(session.getUserId()), password);
    }

    @Override
    public void changeProfile(final @Nullable SessionDTO session, final @Nullable UserDTO user) {
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new SessionValidationException();
        if (user == null) throw new SaveException();
        @Nullable final UserDTO updatedUser =  userService.update(user);
        if (updatedUser == null) throw new SaveException();
    }

    @Override
    public void registerUser(final @Nullable String login, final @Nullable String password) {
        if (login == null || login.isEmpty()) throw new InternalServiceException();
        if (password == null || password.isEmpty()) throw new InternalServiceException();

        @NotNull final UserDTO user = new UserDTO();
        user.setLogin(login);
        user.setPassword(password);
        user.setRoleType(RoleType.USER);
        userService.save(user);
    }
}
