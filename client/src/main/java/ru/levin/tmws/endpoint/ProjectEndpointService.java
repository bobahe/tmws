package ru.levin.tmws.endpoint;

import ru.levin.tmws.api.endpoint.IProjectEndpoint;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-07-18T17:36:28.995+03:00
 * Generated source version: 3.2.7
 *
 */
@WebServiceClient(name = "ProjectEndpointService",
                  wsdlLocation = "http://localhost:80/tm/ProjectEndpoint?wsdl",
                  targetNamespace = "http://endpoint.tmws.levin.ru/")
public class ProjectEndpointService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://endpoint.tmws.levin.ru/", "ProjectEndpointService");
    public final static QName ProjectEndpointPort = new QName("http://endpoint.tmws.levin.ru/", "ProjectEndpointPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:80/tm/ProjectEndpoint?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ProjectEndpointService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "http://localhost:80/tm/ProjectEndpoint?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ProjectEndpointService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ProjectEndpointService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ProjectEndpointService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public ProjectEndpointService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public ProjectEndpointService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public ProjectEndpointService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns IProjectEndpoint
     */
    @WebEndpoint(name = "ProjectEndpointPort")
    public IProjectEndpoint getProjectEndpointPort() {
        return super.getPort(ProjectEndpointPort, IProjectEndpoint.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IProjectEndpoint
     */
    @WebEndpoint(name = "ProjectEndpointPort")
    public IProjectEndpoint getProjectEndpointPort(WebServiceFeature... features) {
        return super.getPort(ProjectEndpointPort, IProjectEndpoint.class, features);
    }

}
