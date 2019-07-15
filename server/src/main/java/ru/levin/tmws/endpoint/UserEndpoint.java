package ru.levin.tmws.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

import javax.inject.Inject;
import javax.jws.WebService;

@WebService(endpointInterface = "ru.levin.tmws.api.endpoint.IUserEndpoint")
public class UserEndpoint implements IUserEndpoint {

    @Nullable
    @Inject
    private ISessionService sessionService;

    @Nullable
    @Inject
    private IUserService userService;

    @Override
    public void changeUserPassword(final @Nullable SessionDTO session, final @Nullable String password) {
        if (sessionService == null || userService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new SessionValidationException();
        if (password == null || password.isEmpty()) throw new SaveException();
        userService.setNewPassword(userService.findById(session.getUserId()), password);
    }

    @Override
    public void changeProfile(final @Nullable SessionDTO session, final @Nullable UserDTO user) {
        if (sessionService == null || userService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new SessionValidationException();
        if (user == null) throw new SaveException();
        @Nullable final UserDTO updatedUser =  userService.update(user);
        if (updatedUser == null) throw new SaveException();
    }

    @Override
    public void registerUser(final @Nullable String login, final @Nullable String password) {
        if (userService == null) throw new InternalServiceException();
        if (login == null || login.isEmpty()) throw new InternalServiceException();
        if (password == null || password.isEmpty()) throw new InternalServiceException();

        @NotNull final UserDTO user = new UserDTO();
        user.setLogin(login);
        user.setPassword(password);
        user.setRoleType(RoleType.USER);
        userService.save(user);
    }
}
