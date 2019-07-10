package ru.levin.tmws.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.IUserEndpoint;
import ru.levin.tmws.entity.RoleType;
import ru.levin.tmws.entity.Session;
import ru.levin.tmws.entity.User;
import ru.levin.tmws.exception.InternalServiceException;
import ru.levin.tmws.exception.SaveException;
import ru.levin.tmws.exception.SessionValidationException;
import ru.levin.tmws.util.ServiceUtil;

import javax.jws.WebService;

@WebService(endpointInterface = "ru.levin.tmws.api.endpoint.IUserEndpoint")
public class UserEndpoint implements IUserEndpoint {

    @Nullable
    private IServiceLocator serviceLocator;

    public UserEndpoint() {
    }

    public UserEndpoint(@Nullable final IServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public void changeUserPassword(final @Nullable Session session, final @Nullable String password) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        if (session.getUserId() == null || session.getUserId().isEmpty()) throw new SessionValidationException();
        if (password == null || password.isEmpty()) throw new SaveException();
        serviceLocator.getUserService().setNewPassword(serviceLocator.getUserService().findById(session.getUserId()), password);
    }

    @Override
    public void changeProfile(final @Nullable Session session, final @Nullable User user) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        if (session.getUserId() == null || session.getUserId().isEmpty()) throw new SessionValidationException();
        if (user == null) throw new SaveException();
        @Nullable final User updatedUser =  serviceLocator.getUserService().update(user);
        if (updatedUser == null) throw new SaveException();
    }

    @Override
    public void registerUser(final @Nullable String login, final @Nullable String password) {
        if (serviceLocator == null) throw new InternalServiceException();
        if (login == null || login.isEmpty()) throw new InternalServiceException();
        if (password == null || password.isEmpty()) throw new InternalServiceException();

        @NotNull final User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setRoleType(RoleType.USER);
        serviceLocator.getUserService().save(user);
    }
}
