package ru.levin.tmws.server.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.server.entity.Session;
import ru.levin.tmws.server.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ISessionEndpoint extends IEndpoint {

    @Nullable
    @WebMethod
    Session openSession(
            @WebParam(name = "login") @Nullable final String login,
            @WebParam(name = "password") @Nullable final String password
    );

    @Nullable
    @WebMethod
    User getUser(@WebParam(name = "ticket") @Nullable final Session session);

    @NotNull
    @WebMethod
    List<Session> getSessionList(@WebParam(name = "ticket") @Nullable final Session session);

    @WebMethod
    void closeSession(@WebParam(name = "ticket") @Nullable final Session session);

    @WebMethod
    void closeSessionAll(@WebParam(name = "ticket") @Nullable final Session session);

}
