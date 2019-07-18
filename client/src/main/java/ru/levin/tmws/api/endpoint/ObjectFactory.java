
package ru.levin.tmws.api.endpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.levin.tmws.api.endpoint package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetServerInfo_QNAME = new QName("http://endpoint.api.tmws.levin.ru/", "getServerInfo");
    private final static QName _GetServerInfoResponse_QNAME = new QName("http://endpoint.api.tmws.levin.ru/", "getServerInfoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.levin.tmws.api.endpoint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetServerInfo }
     * 
     */
    public GetServerInfo createGetServerInfo() {
        return new GetServerInfo();
    }

    /**
     * Create an instance of {@link GetServerInfoResponse }
     * 
     */
    public GetServerInfoResponse createGetServerInfoResponse() {
        return new GetServerInfoResponse();
    }

    /**
     * Create an instance of {@link ServerInfoDTO }
     * 
     */
    public ServerInfoDTO createServerInfoDTO() {
        return new ServerInfoDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServerInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tmws.levin.ru/", name = "getServerInfo")
    public JAXBElement<GetServerInfo> createGetServerInfo(GetServerInfo value) {
        return new JAXBElement<GetServerInfo>(_GetServerInfo_QNAME, GetServerInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServerInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tmws.levin.ru/", name = "getServerInfoResponse")
    public JAXBElement<GetServerInfoResponse> createGetServerInfoResponse(GetServerInfoResponse value) {
        return new JAXBElement<GetServerInfoResponse>(_GetServerInfoResponse_QNAME, GetServerInfoResponse.class, null, value);
    }

}
