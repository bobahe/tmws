package ru.levin.tmws.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levin.tmws.api.endpoint.ISessionEndpoint;
import ru.levin.tmws.api.service.ISessionService;
import ru.levin.tmws.api.service.IUserService;
import ru.levin.tmws.dto.SessionDTO;
import ru.levin.tmws.dto.UserDTO;
import ru.levin.tmws.exception.AccessForbiddenException;
import ru.levin.tmws.exception.AuthorizationException;
import ru.levin.tmws.exception.InternalServiceException;
import ru.levin.tmws.util.ServiceUtil;

import javax.jws.WebService;
import java.util.List;

@Component
@WebService(endpointInterface = "ru.levin.tmws.api.endpoint.ISessionEndpoint")
public class SessionEndpoint implements ISessionEndpoint {

    @NotNull
    private IUserService userService;
    @Autowired
    public void setUserService(@NotNull final IUserService userService) { this.userService = userService; }

    @NotNull
    private ISessionService sessionService;
    @Autowired
    public void setSessionService(@NotNull final ISessionService sessionService) { this.sessionService = sessionService; }

    @Override
    public @Nullable SessionDTO openSession(final @Nullable String login, final @Nullable String password) {
        if (login == null || login.isEmpty()) throw new AuthorizationException();
        if (password == null || password.isEmpty()) throw new AuthorizationException();

        @Nullable final UserDTO user = userService.getUserByLoginAndPassword(login, password);
        if (user == null || user.getId() == null) throw new AuthorizationException();
        @NotNull final List<SessionDTO> sessions = sessionService.findAllByUserId(user.getId());
        if (sessions.size() > 0) return sessions.get(0);
        @NotNull final SessionDTO session = new SessionDTO();
        session.setUserId(user.getId());
        @Nullable final SessionDTO savedSession = sessionService.save(session);
        return savedSession;
    }

    @Override
    public @Nullable UserDTO getUser(final @Nullable SessionDTO session) {
        @NotNull final SessionDTO serverSession = ServiceUtil.checkSession(session, sessionService);
        if (serverSession.getUserId() == null) throw new InternalServiceException();
        @Nullable final UserDTO user = userService.findById(serverSession.getUserId());
        return user;
    }

    @Override
    public @NotNull List<SessionDTO> getSessionList(final @Nullable SessionDTO session) {
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new InternalServiceException();
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), userService);
        if (!isAdmin) throw new AccessForbiddenException();
        return sessionService.getAll();
    }

    @Override
    public void closeSession(final @Nullable SessionDTO session) {
        ServiceUtil.checkSession(session, sessionService);
        sessionService.remove(session);
    }

    @Override
    public void closeSessionAll(final @Nullable SessionDTO session) {
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new InternalServiceException();
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), userService);
        if (!isAdmin) throw new AccessForbiddenException();
        sessionService.removeAll();
    }

}