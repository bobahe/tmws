package ru.levin.tmws.api.endpoint;

import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.entity.Session;
import ru.levin.tmws.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IUserEndpoint extends IEndpoint {

    @WebMethod
    void changeUserPassword(
            @WebParam(name = "ticket") @Nullable final Session session,
            @WebParam(name = "newPassword") @Nullable final String password
    );

    @WebMethod
    void changeProfile(
            @WebParam(name = "ticket") @Nullable final Session session,
            @WebParam(name = "user") @Nullable final User user
    );

    @WebMethod
    void registerUser(
            @WebParam(name = "login") @Nullable final String login,
            @WebParam(name = "password") @Nullable final String password
    );

}