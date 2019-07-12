package ru.levin.tmws.api.endpoint;

import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.dto.SessionDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IAdminEndpoint extends IEndpoint {

    @WebMethod
    void serialize(@WebParam(name = "ticket") @Nullable final SessionDTO session);

    @WebMethod
    void deserialize(@WebParam(name = "ticket") @Nullable final SessionDTO session);

    @WebMethod
    void saveFxmlXml(@WebParam(name = "ticket") @Nullable final SessionDTO session);

    @WebMethod
    void saveFxmlJson(@WebParam(name = "ticket") @Nullable final SessionDTO session);

    @WebMethod
    void loadFxmlXml(@WebParam(name = "ticket") @Nullable final SessionDTO session);

    @WebMethod
    void loadFxmlJson(@WebParam(name = "ticket") @Nullable final SessionDTO session);

    @WebMethod
    void saveJaxbXml(@WebParam(name = "ticket") @Nullable final SessionDTO session);

    @WebMethod
    void saveJaxbJson(@WebParam(name = "ticket") @Nullable final SessionDTO session);

    @WebMethod
    void loadJaxbXml(@WebParam(name = "ticket") @Nullable final SessionDTO session);

    @WebMethod
    void loadJaxbJson(@WebParam(name = "ticket") @Nullable final SessionDTO session);

}
