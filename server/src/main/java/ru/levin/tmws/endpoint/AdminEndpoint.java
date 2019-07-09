package ru.levin.tmws.endpoint;

import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.IAdminEndpoint;
import ru.levin.tmws.entity.Session;
import ru.levin.tmws.exception.AccessForbiddenException;
import ru.levin.tmws.exception.InternalServiceException;
import ru.levin.tmws.util.ServiceUtil;

import javax.jws.WebService;

@WebService(endpointInterface = "ru.levin.tmws.api.endpoint.IAdminEndpoint")
public class AdminEndpoint implements IAdminEndpoint {

    @Nullable private IServiceLocator serviceLocator;

    public AdminEndpoint() {
    }

    public AdminEndpoint(@Nullable final IServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }


    @Override
    public void serialize(final @Nullable Session session) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), serviceLocator.getUserService());
        if (!isAdmin) throw new AccessForbiddenException();
        serviceLocator.getPersistService().serialize(serviceLocator);
    }

    @Override
    public void deserialize(final @Nullable Session session) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), serviceLocator.getUserService());
        if (!isAdmin) throw new AccessForbiddenException();
        serviceLocator.getPersistService().deserialize(serviceLocator);
    }

    @Override
    public void saveFxmlXml(final @Nullable Session session) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), serviceLocator.getUserService());
        if (!isAdmin) throw new AccessForbiddenException();
        serviceLocator.getPersistService().saveFxmlXml(serviceLocator);
    }

    @Override
    public void saveFxmlJson(final @Nullable Session session) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), serviceLocator.getUserService());
        if (!isAdmin) throw new AccessForbiddenException();
        serviceLocator.getPersistService().saveFxmlJson(serviceLocator);
    }

    @Override
    public void loadFxmlXml(final @Nullable Session session) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), serviceLocator.getUserService());
        if (!isAdmin) throw new AccessForbiddenException();
        serviceLocator.getPersistService().loadFxmlXml(serviceLocator);
    }

    @Override
    public void loadFxmlJson(final @Nullable Session session) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), serviceLocator.getUserService());
        if (!isAdmin) throw new AccessForbiddenException();
        serviceLocator.getPersistService().loadFxmlJson(serviceLocator);
    }

    @Override
    public void saveJaxbXml(final @Nullable Session session) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), serviceLocator.getUserService());
        if (!isAdmin) throw new AccessForbiddenException();
        serviceLocator.getPersistService().saveJaxbXml(serviceLocator);
    }

    @Override
    public void saveJaxbJson(final @Nullable Session session) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), serviceLocator.getUserService());
        if (!isAdmin) throw new AccessForbiddenException();
        serviceLocator.getPersistService().saveJaxbJson(serviceLocator);
    }

    @Override
    public void loadJaxbXml(final @Nullable Session session) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), serviceLocator.getUserService());
        if (!isAdmin) throw new AccessForbiddenException();
        serviceLocator.getPersistService().loadJaxbXml(serviceLocator);
    }

    @Override
    public void loadJaxbJson(final @Nullable Session session) {
        if (serviceLocator == null) throw new InternalServiceException();
        ServiceUtil.checkSession(session, serviceLocator.getSessionService());
        final boolean isAdmin = ServiceUtil.isAdmin(session.getUserId(), serviceLocator.getUserService());
        if (!isAdmin) throw new AccessForbiddenException();
        serviceLocator.getPersistService().loadJaxbJson(serviceLocator);
    }

}
