package ru.levin.tmws.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.ISessionEndpoint;
import ru.levin.tmws.dto.SessionDTO;
import ru.levin.tmws.dto.UserDTO;
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
    public @Nullable SessionDTO openSession(final @Nullable String login, final @Nullable String password) {
        if (serviceLocator == null) throw new InternalServiceException();
        if (login == null || login.isEmpty()) throw new AuthorizationException();
        if (password == null || password.isEmpty()) throw new AuthorizationException();

        @Nullable final UserDTO user = serviceLocator.getUserService().getUserByLoginAndPassword(login, password);
        if (user == null || user.getId() == null) throw new AuthorizationException();
        @NotNull final List<SessionDTO> sessions = serviceLocator.getSessionService().findAllByUserId(user.getId());
        if (sessions.size() > 0) return sessions.get(0);
        @NotNull final SessionDTO session = new SessionDTO();
        session.setUserId(user.getId());
        @Nullable final SessionDTO savedSession = serviceLocator.getSessionService().save(session);
        return savedSession;
    }

    @Override
    public @Nullable UserDTO getUser(final @Nullable SessionDTO session) {
        if (serviceLocator == null) throw new InternalServiceException();
        @NotNull final SessionDTO serverSession = ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        if (serverSession.getUserId() == null) throw new InternalServiceException();
        @Nullable final UserDTO user = serviceLocator.getUserService().findById(serverSession.getUserId());
        return user;
    }

    @Override
    public @NotNull List<SessionDTO> getSessionList(final @Nullable SessionDTO session) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        if (session.getUserId() == null) throw new InternalServiceException();
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), serviceLocator.getUserService());
        if (!isAdmin) throw new AccessForbiddenException();
        return serviceLocator.getSessionService().getAll();
    }

    @Override
    public void closeSession(final @Nullable SessionDTO session) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        serviceLocator.getSessionService().remove(session);
    }

    @Override
    public void closeSessionAll(final @Nullable SessionDTO session) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        if (session.getUserId() == null) throw new InternalServiceException();
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), serviceLocator.getUserService());
        if (!isAdmin) throw new AccessForbiddenException();
        serviceLocator.getSessionService().removeAll();
    }

}