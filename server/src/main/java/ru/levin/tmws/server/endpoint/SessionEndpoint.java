package ru.levin.tmws.server.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.server.api.IServiceLocator;
import ru.levin.tmws.server.api.endpoint.ISessionEndpoint;
import ru.levin.tmws.server.entity.Session;
import ru.levin.tmws.server.entity.User;
import ru.levin.tmws.server.exception.AccessForbiddenException;
import ru.levin.tmws.server.exception.AuthorizationException;
import ru.levin.tmws.server.exception.InternalServiceException;
import ru.levin.tmws.server.util.ServiceUtil;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.levin.tmws.server.api.endpoint.ISessionEndpoint")
public class SessionEndpoint implements ISessionEndpoint {

    @Nullable
    private IServiceLocator bootstrap;

    public SessionEndpoint() {
    }

    public SessionEndpoint(@NotNull final IServiceLocator bootstrap) {
        this.bootstrap = bootstrap;
    }

    @Override
    public @Nullable Session openSession(final @Nullable String login, final @Nullable String password) {
        if (bootstrap == null) throw new InternalServiceException();
        if (login == null || login.isEmpty()) throw new AuthorizationException();
        if (password == null || password.isEmpty()) throw new AuthorizationException();

        @Nullable final User user = bootstrap.getUserService().getUserByLoginAndPassword(login, password);
        if (user == null || user.getId() == null) throw new AuthorizationException();
        @NotNull final List<Session> sessions = bootstrap.getSessionService().findAllByUserId(user.getId());
        if (sessions.size() > 0) return sessions.get(0);
        @NotNull final Session session = new Session();
        session.setUserId(user.getId());
        @Nullable final Session savedSession = bootstrap.getSessionService().save(session);
        return savedSession;
    }

    @Override
    public @Nullable User getUser(final @Nullable Session session) {
        if (bootstrap == null) throw new InternalServiceException();
        @NotNull final Session serverSession = ServiceUtil.checkSession(session, bootstrap.getSessionService());
        @Nullable final User user = bootstrap.getUserService().findById(serverSession.getUserId());
        return user;
    }

    @Override
    public @NotNull List<Session> getSessionList(final @Nullable Session session) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), bootstrap.getUserService());
        if (!isAdmin) throw new AccessForbiddenException();
        return bootstrap.getSessionService().getAll();
    }

    @Override
    public void closeSession(final @Nullable Session session) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        bootstrap.getSessionService().remove(session);
    }

    @Override
    public void closeSessionAll(final @Nullable Session session) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), bootstrap.getUserService());
        if (!isAdmin) throw new AccessForbiddenException();
        bootstrap.getSessionService().removeAll();
    }

}