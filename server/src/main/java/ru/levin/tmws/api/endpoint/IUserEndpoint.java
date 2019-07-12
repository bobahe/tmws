package ru.levin.tmws.api.endpoint;

import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.dto.SessionDTO;
import ru.levin.tmws.dto.UserDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IUserEndpoint extends IEndpoint {

    @WebMethod
    void changeUserPassword(
            @WebParam(name = "ticket") @Nullable final SessionDTO session,
            @WebParam(name = "newPassword") @Nullable final String password
    );

    @WebMethod
    void changeProfile(
            @WebParam(name = "ticket") @Nullable final SessionDTO session,
            @WebParam(name = "user") @Nullable final UserDTO user
    );

    @WebMethod
    void registerUser(
            @WebParam(name = "login") @Nullable final String login,
            @WebParam(name = "password") @Nullable final String password
    );

}
