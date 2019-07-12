package ru.levin.tmws.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.dto.SessionDTO;
import ru.levin.tmws.dto.UserDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ISessionEndpoint extends IEndpoint {

    @Nullable
    @WebMethod
    SessionDTO openSession(
            @WebParam(name = "login") @Nullable final String login,
            @WebParam(name = "password") @Nullable final String password
    );

    @Nullable
    @WebMethod
    UserDTO getUser(@WebParam(name = "ticket") @Nullable final SessionDTO session);

    @NotNull
    @WebMethod
    List<SessionDTO> getSessionList(@WebParam(name = "ticket") @Nullable final SessionDTO session);

    @WebMethod
    void closeSession(@WebParam(name = "ticket") @Nullable final SessionDTO session);

    @WebMethod
    void closeSessionAll(@WebParam(name = "ticket") @Nullable final SessionDTO session);

}
