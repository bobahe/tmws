package ru.levin.tmws.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levin.tmws.api.endpoint.IAdminEndpoint;
import ru.levin.tmws.api.service.IPersistService;
import ru.levin.tmws.api.service.ISessionService;
import ru.levin.tmws.api.service.IUserService;
import ru.levin.tmws.dto.SessionDTO;
import ru.levin.tmws.exception.AccessForbiddenException;
import ru.levin.tmws.exception.InternalServiceException;
import ru.levin.tmws.util.ServiceUtil;

import javax.jws.WebService;

@Component
@WebService(endpointInterface = "ru.levin.tmws.api.endpoint.IAdminEndpoint")
public class AdminEndpoint implements IAdminEndpoint {

    @NotNull
    private ISessionService sessionService;
    @Autowired
    public void setSessionService(@NotNull final ISessionService service) { this.sessionService = service; }

    @NotNull
    private IUserService userService;
    @Autowired
    public void setUserService(@NotNull final IUserService service) { this.userService = service; }

    @NotNull
    private IPersistService persistService;
    @Autowired
    public void setPersistService(@NotNull final IPersistService service) { this.persistService = service; }

    @Override
    public void serialize(final @Nullable SessionDTO session) {
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new InternalServiceException();
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), userService);
        if (!isAdmin) throw new AccessForbiddenException();
        persistService.serialize();
    }

    @Override
    public void deserialize(final @Nullable SessionDTO session) {
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new InternalServiceException();
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), userService);
        if (!isAdmin) throw new AccessForbiddenException();
        persistService.deserialize();
    }

    @Override
    public void saveFxmlXml(final @Nullable SessionDTO session) {
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new InternalServiceException();
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), userService);
        if (!isAdmin) throw new AccessForbiddenException();
        persistService.saveFxmlXml();
    }

    @Override
    public void saveFxmlJson(final @Nullable SessionDTO session) {
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new InternalServiceException();
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), userService);
        if (!isAdmin) throw new AccessForbiddenException();
        persistService.saveFxmlJson();
    }

    @Override
    public void loadFxmlXml(final @Nullable SessionDTO session) {
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new InternalServiceException();
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), userService);
        if (!isAdmin) throw new AccessForbiddenException();
        persistService.loadFxmlXml();
    }

    @Override
    public void loadFxmlJson(final @Nullable SessionDTO session) {
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new InternalServiceException();
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), userService);
        if (!isAdmin) throw new AccessForbiddenException();
        persistService.loadFxmlJson();
    }

    @Override
    public void saveJaxbXml(final @Nullable SessionDTO session) {
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new InternalServiceException();
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), userService);
        if (!isAdmin) throw new AccessForbiddenException();
        persistService.saveJaxbXml();
    }

    @Override
    public void saveJaxbJson(final @Nullable SessionDTO session) {
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new InternalServiceException();
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), userService);
        if (!isAdmin) throw new AccessForbiddenException();
        persistService.saveJaxbJson();
    }

    @Override
    public void loadJaxbXml(final @Nullable SessionDTO session) {
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new InternalServiceException();
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), userService);
        if (!isAdmin) throw new AccessForbiddenException();
        persistService.loadJaxbXml();
    }

    @Override
    public void loadJaxbJson(final @Nullable SessionDTO session) {
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new InternalServiceException();
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), userService);
        if (!isAdmin) throw new AccessForbiddenException();
        persistService.loadJaxbJson();
    }

}
