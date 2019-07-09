package ru.levin.tmws.server.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.server.api.IServiceLocator;
import ru.levin.tmws.server.api.endpoint.IUserEndpoint;
import ru.levin.tmws.server.entity.RoleType;
import ru.levin.tmws.server.entity.Session;
import ru.levin.tmws.server.entity.User;
import ru.levin.tmws.server.exception.AuthorizationException;
import ru.levin.tmws.server.exception.InternalServiceException;
import ru.levin.tmws.server.exception.SaveException;
import ru.levin.tmws.server.exception.SessionValidationException;
import ru.levin.tmws.server.util.ServiceUtil;

import javax.jws.WebService;

@WebService(endpointInterface = "ru.levin.tmws.server.api.endpoint.IUserEndpoint")
public class UserEndpoint implements IUserEndpoint {

    @Nullable
    private IServiceLocator bootstrap;

    public UserEndpoint() {
    }

    public UserEndpoint(@Nullable final IServiceLocator bootstrap) {
        this.bootstrap = bootstrap;
    }

    @Override
    public void changeUserPassword(final @Nullable Session session, final @Nullable String password) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        if (session.getUserId() == null || session.getUserId().isEmpty()) throw new SessionValidationException();
        if (password == null || password.isEmpty()) throw new SaveException();
        bootstrap.getUserService().setNewPassword(bootstrap.getUserService().findById(session.getUserId()), password);
    }

    @Override
    public void changeLogin(final @Nullable Session session, final @Nullable String login) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        if (session.getUserId() == null || session.getUserId().isEmpty()) throw new SessionValidationException();
        if (login == null || login.isEmpty()) throw new SaveException();
        @Nullable final User user = bootstrap.getUserService().findById(session.getUserId());
        if (user == null) throw new SaveException();
        user.setLogin(login);
        bootstrap.getUserService().update(user);
    }

    @Override
    public void registerUser(final @Nullable String login, final @Nullable String password) {
        if (bootstrap == null) throw new InternalServiceException();
        if (login == null || login.isEmpty()) throw new InternalServiceException();
        if (password == null || password.isEmpty()) throw new InternalServiceException();

        @NotNull final User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setRoleType(RoleType.USER);
        bootstrap.getUserService().save(user);
    }
}
