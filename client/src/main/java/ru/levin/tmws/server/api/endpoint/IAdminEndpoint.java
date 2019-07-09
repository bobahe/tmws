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
 * 2019-07-09T11:18:51.492+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", name = "IAdminEndpoint")
@XmlSeeAlso({ObjectFactory.class})
public interface IAdminEndpoint {

    @WebMethod
    @Action(input = "http://endpoint.api.server.tmws.levin.ru/IAdminEndpoint/deserializeRequest", output = "http://endpoint.api.server.tmws.levin.ru/IAdminEndpoint/deserializeResponse")
    @RequestWrapper(localName = "deserialize", targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", className = "ru.levin.tmws.server.api.endpoint.Deserialize")
    @ResponseWrapper(localName = "deserializeResponse", targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", className = "ru.levin.tmws.server.api.endpoint.DeserializeResponse")
    public void deserialize(
        @WebParam(name = "ticket", targetNamespace = "")
        ru.levin.tmws.server.api.endpoint.Session ticket
    );

    @WebMethod
    @Action(input = "http://endpoint.api.server.tmws.levin.ru/IAdminEndpoint/loadFxmlXmlRequest", output = "http://endpoint.api.server.tmws.levin.ru/IAdminEndpoint/loadFxmlXmlResponse")
    @RequestWrapper(localName = "loadFxmlXml", targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", className = "ru.levin.tmws.server.api.endpoint.LoadFxmlXml")
    @ResponseWrapper(localName = "loadFxmlXmlResponse", targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", className = "ru.levin.tmws.server.api.endpoint.LoadFxmlXmlResponse")
    public void loadFxmlXml(
        @WebParam(name = "ticket", targetNamespace = "")
        ru.levin.tmws.server.api.endpoint.Session ticket
    );

    @WebMethod
    @Action(input = "http://endpoint.api.server.tmws.levin.ru/IAdminEndpoint/loadJaxbXmlRequest", output = "http://endpoint.api.server.tmws.levin.ru/IAdminEndpoint/loadJaxbXmlResponse")
    @RequestWrapper(localName = "loadJaxbXml", targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", className = "ru.levin.tmws.server.api.endpoint.LoadJaxbXml")
    @ResponseWrapper(localName = "loadJaxbXmlResponse", targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", className = "ru.levin.tmws.server.api.endpoint.LoadJaxbXmlResponse")
    public void loadJaxbXml(
        @WebParam(name = "ticket", targetNamespace = "")
        ru.levin.tmws.server.api.endpoint.Session ticket
    );

    @WebMethod
    @Action(input = "http://endpoint.api.server.tmws.levin.ru/IAdminEndpoint/serializeRequest", output = "http://endpoint.api.server.tmws.levin.ru/IAdminEndpoint/serializeResponse")
    @RequestWrapper(localName = "serialize", targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", className = "ru.levin.tmws.server.api.endpoint.Serialize")
    @ResponseWrapper(localName = "serializeResponse", targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", className = "ru.levin.tmws.server.api.endpoint.SerializeResponse")
    public void serialize(
        @WebParam(name = "ticket", targetNamespace = "")
        ru.levin.tmws.server.api.endpoint.Session ticket
    );

    @WebMethod
    @Action(input = "http://endpoint.api.server.tmws.levin.ru/IAdminEndpoint/loadFxmlJsonRequest", output = "http://endpoint.api.server.tmws.levin.ru/IAdminEndpoint/loadFxmlJsonResponse")
    @RequestWrapper(localName = "loadFxmlJson", targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", className = "ru.levin.tmws.server.api.endpoint.LoadFxmlJson")
    @ResponseWrapper(localName = "loadFxmlJsonResponse", targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", className = "ru.levin.tmws.server.api.endpoint.LoadFxmlJsonResponse")
    public void loadFxmlJson(
        @WebParam(name = "ticket", targetNamespace = "")
        ru.levin.tmws.server.api.endpoint.Session ticket
    );

    @WebMethod
    @Action(input = "http://endpoint.api.server.tmws.levin.ru/IAdminEndpoint/saveJaxbJsonRequest", output = "http://endpoint.api.server.tmws.levin.ru/IAdminEndpoint/saveJaxbJsonResponse")
    @RequestWrapper(localName = "saveJaxbJson", targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", className = "ru.levin.tmws.server.api.endpoint.SaveJaxbJson")
    @ResponseWrapper(localName = "saveJaxbJsonResponse", targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", className = "ru.levin.tmws.server.api.endpoint.SaveJaxbJsonResponse")
    public void saveJaxbJson(
        @WebParam(name = "ticket", targetNamespace = "")
        ru.levin.tmws.server.api.endpoint.Session ticket
    );

    @WebMethod
    @Action(input = "http://endpoint.api.server.tmws.levin.ru/IAdminEndpoint/saveFxmlXmlRequest", output = "http://endpoint.api.server.tmws.levin.ru/IAdminEndpoint/saveFxmlXmlResponse")
    @RequestWrapper(localName = "saveFxmlXml", targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", className = "ru.levin.tmws.server.api.endpoint.SaveFxmlXml")
    @ResponseWrapper(localName = "saveFxmlXmlResponse", targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", className = "ru.levin.tmws.server.api.endpoint.SaveFxmlXmlResponse")
    public void saveFxmlXml(
        @WebParam(name = "ticket", targetNamespace = "")
        ru.levin.tmws.server.api.endpoint.Session ticket
    );

    @WebMethod
    @Action(input = "http://endpoint.api.server.tmws.levin.ru/IAdminEndpoint/saveJaxbXmlRequest", output = "http://endpoint.api.server.tmws.levin.ru/IAdminEndpoint/saveJaxbXmlResponse")
    @RequestWrapper(localName = "saveJaxbXml", targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", className = "ru.levin.tmws.server.api.endpoint.SaveJaxbXml")
    @ResponseWrapper(localName = "saveJaxbXmlResponse", targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", className = "ru.levin.tmws.server.api.endpoint.SaveJaxbXmlResponse")
    public void saveJaxbXml(
        @WebParam(name = "ticket", targetNamespace = "")
        ru.levin.tmws.server.api.endpoint.Session ticket
    );

    @WebMethod
    @Action(input = "http://endpoint.api.server.tmws.levin.ru/IAdminEndpoint/saveFxmlJsonRequest", output = "http://endpoint.api.server.tmws.levin.ru/IAdminEndpoint/saveFxmlJsonResponse")
    @RequestWrapper(localName = "saveFxmlJson", targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", className = "ru.levin.tmws.server.api.endpoint.SaveFxmlJson")
    @ResponseWrapper(localName = "saveFxmlJsonResponse", targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", className = "ru.levin.tmws.server.api.endpoint.SaveFxmlJsonResponse")
    public void saveFxmlJson(
        @WebParam(name = "ticket", targetNamespace = "")
        ru.levin.tmws.server.api.endpoint.Session ticket
    );

    @WebMethod
    @Action(input = "http://endpoint.api.server.tmws.levin.ru/IAdminEndpoint/loadJaxbJsonRequest", output = "http://endpoint.api.server.tmws.levin.ru/IAdminEndpoint/loadJaxbJsonResponse")
    @RequestWrapper(localName = "loadJaxbJson", targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", className = "ru.levin.tmws.server.api.endpoint.LoadJaxbJson")
    @ResponseWrapper(localName = "loadJaxbJsonResponse", targetNamespace = "http://endpoint.api.server.tmws.levin.ru/", className = "ru.levin.tmws.server.api.endpoint.LoadJaxbJsonResponse")
    public void loadJaxbJson(
        @WebParam(name = "ticket", targetNamespace = "")
        ru.levin.tmws.server.api.endpoint.Session ticket
    );
}
