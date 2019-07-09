package ru.levin.tmws.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.ISessionEndpoint;
import ru.levin.tmws.entity.Session;
import ru.levin.tmws.entity.User;
import ru.levin.tmws.exception.AccessForbiddenException;
import ru.levin.tmws.exception.AuthorizationException;
import ru.levin.tmws.exception.InternalServiceException;
import ru.levin.tmws.util.ServiceUtil;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.levin.tmws.api.endpoint.ISessionEndpoint")
public class SessionEndpoint implements ISessionEndpoint {

    @Nullable
    private IServiceLocator serviceLocator;

    public SessionEndpoint() {
    }

    public SessionEndpoint(@NotNull final IServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public @Nullable Session openSession(final @Nullable String login, final @Nullable String password) {
        if (serviceLocator == null) throw new InternalServiceException();
        if (login == null || login.isEmpty()) throw new AuthorizationException();
        if (password == null || password.isEmpty()) throw new AuthorizationException();

        @Nullable final User user = serviceLocator.getUserService().getUserByLoginAndPassword(login, password);
        if (user == null || user.getId() == null) throw new AuthorizationException();
        @NotNull final List<Session> sessions = serviceLocator.getSessionService().findAllByUserId(user.getId());
        if (sessions.size() > 0) return sessions.get(0);
        @NotNull final Session session = new Session();
        session.setUserId(user.getId());
        @Nullable final Session savedSession = serviceLocator.getSessionService().save(session);
        return savedSession;
    }

    @Override
    public @Nullable User getUser(final @Nullable Session session) {
        if (serviceLocator == null) throw new InternalServiceException();
        @NotNull final Session serverSession = ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        @Nullable final User user = serviceLocator.getUserService().findById(serverSession.getUserId());
        return user;
    }

    @Override
    public @NotNull List<Session> getSessionList(final @Nullable Session session) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), serviceLocator.getUserService());
        if (!isAdmin) throw new AccessForbiddenException();
        return serviceLocator.getSessionService().getAll();
    }

    @Override
    public void closeSession(final @Nullable Session session) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        serviceLocator.getSessionService().remove(session);
    }

    @Override
    public void closeSessionAll(final @Nullable Session session) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), serviceLocator.getUserService());
        if (!isAdmin) throw new AccessForbiddenException();
        serviceLocator.getSessionService().removeAll();
    }

}