package ru.levin.tmws.server.api.endpoint;

import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.server.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IAdminEndpoint extends IEndpoint {

    @WebMethod
    void serialize(@WebParam(name = "ticket") @Nullable final Session session);

    @WebMethod
    void deserialize(@WebParam(name = "ticket") @Nullable final Session session);

    @WebMethod
    void saveFxmlXml(@WebParam(name = "ticket") @Nullable final Session session);

    @WebMethod
    void saveFxmlJson(@WebParam(name = "ticket") @Nullable final Session session);

    @WebMethod
    void loadFxmlXml(@WebParam(name = "ticket") @Nullable final Session session);

    @WebMethod
    void loadFxmlJson(@WebParam(name = "ticket") @Nullable final Session session);

    @WebMethod
    void saveJaxbXml(@WebParam(name = "ticket") @Nullable final Session session);

    @WebMethod
    void saveJaxbJson(@WebParam(name = "ticket") @Nullable final Session session);

    @WebMethod
    void loadJaxbXml(@WebParam(name = "ticket") @Nullable final Session session);

    @WebMethod
    void loadJaxbJson(@WebParam(name = "ticket") @Nullable final Session session);

}
