package ru.levin.tmws.endpoint;

import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.endpoint.IAdminEndpoint;
import ru.levin.tmws.api.service.IPersistService;
import ru.levin.tmws.api.service.ISessionService;
import ru.levin.tmws.api.service.IUserService;
import ru.levin.tmws.dto.SessionDTO;
import ru.levin.tmws.exception.AccessForbiddenException;
import ru.levin.tmws.exception.InternalServiceException;
import ru.levin.tmws.util.ServiceUtil;

import javax.inject.Inject;
import javax.jws.WebService;

@WebService(endpointInterface = "ru.levin.tmws.api.endpoint.IAdminEndpoint")
public class AdminEndpoint implements IAdminEndpoint {

    @Nullable
    @Inject
    private ISessionService sessionService;

    @Nullable
    @Inject
    private IUserService userService;

    @Nullable
    @Inject
    private IPersistService persistService;

    @Override
    public void serialize(final @Nullable SessionDTO session) {
        if (sessionService == null || userService == null || persistService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new InternalServiceException();
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), userService);
        if (!isAdmin) throw new AccessForbiddenException();
        persistService.serialize();
    }

    @Override
    public void deserialize(final @Nullable SessionDTO session) {
        if (sessionService == null || userService == null || persistService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new InternalServiceException();
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), userService);
        if (!isAdmin) throw new AccessForbiddenException();
        persistService.deserialize();
    }

    @Override
    public void saveFxmlXml(final @Nullable SessionDTO session) {
        if (sessionService == null || userService == null || persistService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new InternalServiceException();
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), userService);
        if (!isAdmin) throw new AccessForbiddenException();
        persistService.saveFxmlXml();
    }

    @Override
    public void saveFxmlJson(final @Nullable SessionDTO session) {
        if (sessionService == null || userService == null || persistService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new InternalServiceException();
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), userService);
        if (!isAdmin) throw new AccessForbiddenException();
        persistService.saveFxmlJson();
    }

    @Override
    public void loadFxmlXml(final @Nullable SessionDTO session) {
        if (sessionService == null || userService == null || persistService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new InternalServiceException();
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), userService);
        if (!isAdmin) throw new AccessForbiddenException();
        persistService.loadFxmlXml();
    }

    @Override
    public void loadFxmlJson(final @Nullable SessionDTO session) {
        if (sessionService == null || userService == null || persistService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new InternalServiceException();
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), userService);
        if (!isAdmin) throw new AccessForbiddenException();
        persistService.loadFxmlJson();
    }

    @Override
    public void saveJaxbXml(final @Nullable SessionDTO session) {
        if (sessionService == null || userService == null || persistService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new InternalServiceException();
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), userService);
        if (!isAdmin) throw new AccessForbiddenException();
        persistService.saveJaxbXml();
    }

    @Override
    public void saveJaxbJson(final @Nullable SessionDTO session) {
        if (sessionService == null || userService == null || persistService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new InternalServiceException();
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), userService);
        if (!isAdmin) throw new AccessForbiddenException();
        persistService.saveJaxbJson();
    }

    @Override
    public void loadJaxbXml(final @Nullable SessionDTO session) {
        if (sessionService == null || userService == null || persistService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new InternalServiceException();
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), userService);
        if (!isAdmin) throw new AccessForbiddenException();
        persistService.loadJaxbXml();
    }

    @Override
    public void loadJaxbJson(final @Nullable SessionDTO session) {
        if (sessionService == null || userService == null || persistService == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, sessionService);
        if (session.getUserId() == null) throw new InternalServiceException();
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), userService);
        if (!isAdmin) throw new AccessForbiddenException();
        persistService.loadJaxbJson();
    }

}
