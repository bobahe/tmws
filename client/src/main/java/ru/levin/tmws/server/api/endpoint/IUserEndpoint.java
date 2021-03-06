package ru.levin.tmws.server.api.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-07-09T11:18:51.394+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", name = "IUserEndpoint")
@XmlSeeAlso({ObjectFactory.class})
public interface IUserEndpoint {

    @WebMethod
    @Action(input = "http://endpoint.api.server.tmws.levin.ru/IUserEndpoint/changeUserPasswordRequest", output = "http://endpoint.api.server.tmws.levin.ru/IUserEndpoint/changeUserPasswordResponse")
    @RequestWrapper(localName = "changeUserPassword", targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", className = "ru.levin.tmws.server.api.endpoint.ChangeUserPassword")
    @ResponseWrapper(localName = "changeUserPasswordResponse", targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", className = "ru.levin.tmws.server.api.endpoint.ChangeUserPasswordResponse")
    public void changeUserPassword(
        @WebParam(name = "ticket", targetNamespace = "")
        ru.levin.tmws.server.api.endpoint.Session ticket,
        @WebParam(name = "newPassword", targetNamespace = "")
        java.lang.String newPassword
    );

    @WebMethod
    @Action(input = "http://endpoint.api.server.tmws.levin.ru/IUserEndpoint/registerUserRequest", output = "http://endpoint.api.server.tmws.levin.ru/IUserEndpoint/registerUserResponse")
    @RequestWrapper(localName = "registerUser", targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", className = "ru.levin.tmws.server.api.endpoint.RegisterUser")
    @ResponseWrapper(localName = "registerUserResponse", targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", className = "ru.levin.tmws.server.api.endpoint.RegisterUserResponse")
    public void registerUser(
        @WebParam(name = "login", targetNamespace = "")
        java.lang.String login,
        @WebParam(name = "password", targetNamespace = "")
        java.lang.String password
    );

    @WebMethod
    @Action(input = "http://endpoint.api.server.tmws.levin.ru/IUserEndpoint/changeLoginRequest", output = "http://endpoint.api.server.tmws.levin.ru/IUserEndpoint/changeLoginResponse")
    @RequestWrapper(localName = "changeLogin", targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", className = "ru.levin.tmws.server.api.endpoint.ChangeLogin")
    @ResponseWrapper(localName = "changeLoginResponse", targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", className = "ru.levin.tmws.server.api.endpoint.ChangeLoginResponse")
    public void changeLogin(
        @WebParam(name = "ticket", targetNamespace = "")
        ru.levin.tmws.server.api.endpoint.Session ticket,
        @WebParam(name = "login", targetNamespace = "")
        java.lang.String login
    );
}
