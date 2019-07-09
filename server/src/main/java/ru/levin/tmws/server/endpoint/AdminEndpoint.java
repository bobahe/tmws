package ru.levin.tmws.server.endpoint;

import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.server.api.IServiceLocator;
import ru.levin.tmws.server.api.endpoint.IAdminEndpoint;
import ru.levin.tmws.server.entity.Session;
import ru.levin.tmws.server.exception.AccessForbiddenException;
import ru.levin.tmws.server.exception.InternalServiceException;
import ru.levin.tmws.server.util.ServiceUtil;

import javax.jws.WebService;

@WebService(endpointInterface = "ru.levin.tmws.server.api.endpoint.IAdminEndpoint")
public class AdminEndpoint implements IAdminEndpoint {

    @Nullable private IServiceLocator bootstrap;

    public AdminEndpoint() {
    }

    public AdminEndpoint(@Nullable final IServiceLocator bootstrap) {
        this.bootstrap = bootstrap;
    }


    @Override
    public void serialize(final @Nullable Session session) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), bootstrap.getUserService());
        if (!isAdmin) throw new AccessForbiddenException();
        bootstrap.getPersistService().serialize(bootstrap);
    }

    @Override
    public void deserialize(final @Nullable Session session) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), bootstrap.getUserService());
        if (!isAdmin) throw new AccessForbiddenException();
        bootstrap.getPersistService().deserialize(bootstrap);
    }

    @Override
    public void saveFxmlXml(final @Nullable Session session) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), bootstrap.getUserService());
        if (!isAdmin) throw new AccessForbiddenException();
        bootstrap.getPersistService().saveFxmlXml(bootstrap);
    }

    @Override
    public void saveFxmlJson(final @Nullable Session session) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), bootstrap.getUserService());
        if (!isAdmin) throw new AccessForbiddenException();
        bootstrap.getPersistService().saveFxmlJson(bootstrap);
    }

    @Override
    public void loadFxmlXml(final @Nullable Session session) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), bootstrap.getUserService());
        if (!isAdmin) throw new AccessForbiddenException();
        bootstrap.getPersistService().loadFxmlXml(bootstrap);
    }

    @Override
    public void loadFxmlJson(final @Nullable Session session) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), bootstrap.getUserService());
        if (!isAdmin) throw new AccessForbiddenException();
        bootstrap.getPersistService().loadFxmlJson(bootstrap);
    }

    @Override
    public void saveJaxbXml(final @Nullable Session session) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), bootstrap.getUserService());
        if (!isAdmin) throw new AccessForbiddenException();
        bootstrap.getPersistService().saveJaxbXml(bootstrap);
    }

    @Override
    public void saveJaxbJson(final @Nullable Session session) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), bootstrap.getUserService());
        if (!isAdmin) throw new AccessForbiddenException();
        bootstrap.getPersistService().saveJaxbJson(bootstrap);
    }

    @Override
    public void loadJaxbXml(final @Nullable Session session) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), bootstrap.getUserService());
        if (!isAdmin) throw new AccessForbiddenException();
        bootstrap.getPersistService().loadJaxbXml(bootstrap);
    }

    @Override
    public void loadJaxbJson(final @Nullable Session session) {
        if (bootstrap == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, bootstrap.getSessionService());
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), bootstrap.getUserService());
        if (!isAdmin) throw new AccessForbiddenException();
        bootstrap.getPersistService().loadJaxbJson(bootstrap);
    }

}
