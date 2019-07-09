
package ru.levin.tmws.server.api.endpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.levin.tmws.server.api.endpoint package. 
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

    private final static QName _Deserialize_QNAME = new QName("http://endpoint.api.server.tmws.levin.ru/", "deserialize");
    private final static QName _DeserializeResponse_QNAME = new QName("http://endpoint.api.server.tmws.levin.ru/", "deserializeResponse");
    private final static QName _LoadFxmlJson_QNAME = new QName("http://endpoint.api.server.tmws.levin.ru/", "loadFxmlJson");
    private final static QName _LoadFxmlJsonResponse_QNAME = new QName("http://endpoint.api.server.tmws.levin.ru/", "loadFxmlJsonResponse");
    private final static QName _LoadFxmlXml_QNAME = new QName("http://endpoint.api.server.tmws.levin.ru/", "loadFxmlXml");
    private final static QName _LoadFxmlXmlResponse_QNAME = new QName("http://endpoint.api.server.tmws.levin.ru/", "loadFxmlXmlResponse");
    private final static QName _LoadJaxbJson_QNAME = new QName("http://endpoint.api.server.tmws.levin.ru/", "loadJaxbJson");
    private final static QName _LoadJaxbJsonResponse_QNAME = new QName("http://endpoint.api.server.tmws.levin.ru/", "loadJaxbJsonResponse");
    private final static QName _LoadJaxbXml_QNAME = new QName("http://endpoint.api.server.tmws.levin.ru/", "loadJaxbXml");
    private final static QName _LoadJaxbXmlResponse_QNAME = new QName("http://endpoint.api.server.tmws.levin.ru/", "loadJaxbXmlResponse");
    private final static QName _SaveFxmlJson_QNAME = new QName("http://endpoint.api.server.tmws.levin.ru/", "saveFxmlJson");
    private final static QName _SaveFxmlJsonResponse_QNAME = new QName("http://endpoint.api.server.tmws.levin.ru/", "saveFxmlJsonResponse");
    private final static QName _SaveFxmlXml_QNAME = new QName("http://endpoint.api.server.tmws.levin.ru/", "saveFxmlXml");
    private final static QName _SaveFxmlXmlResponse_QNAME = new QName("http://endpoint.api.server.tmws.levin.ru/", "saveFxmlXmlResponse");
    private final static QName _SaveJaxbJson_QNAME = new QName("http://endpoint.api.server.tmws.levin.ru/", "saveJaxbJson");
    private final static QName _SaveJaxbJsonResponse_QNAME = new QName("http://endpoint.api.server.tmws.levin.ru/", "saveJaxbJsonResponse");
    private final static QName _SaveJaxbXml_QNAME = new QName("http://endpoint.api.server.tmws.levin.ru/", "saveJaxbXml");
    private final static QName _SaveJaxbXmlResponse_QNAME = new QName("http://endpoint.api.server.tmws.levin.ru/", "saveJaxbXmlResponse");
    private final static QName _Serialize_QNAME = new QName("http://endpoint.api.server.tmws.levin.ru/", "serialize");
    private final static QName _SerializeResponse_QNAME = new QName("http://endpoint.api.server.tmws.levin.ru/", "serializeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.levin.tmws.server.api.endpoint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Deserialize }
     * 
     */
    public Deserialize createDeserialize() {
        return new Deserialize();
    }

    /**
     * Create an instance of {@link DeserializeResponse }
     * 
     */
    public DeserializeResponse createDeserializeResponse() {
        return new DeserializeResponse();
    }

    /**
     * Create an instance of {@link LoadFxmlJson }
     * 
     */
    public LoadFxmlJson createLoadFxmlJson() {
        return new LoadFxmlJson();
    }

    /**
     * Create an instance of {@link LoadFxmlJsonResponse }
     * 
     */
    public LoadFxmlJsonResponse createLoadFxmlJsonResponse() {
        return new LoadFxmlJsonResponse();
    }

    /**
     * Create an instance of {@link LoadFxmlXml }
     * 
     */
    public LoadFxmlXml createLoadFxmlXml() {
        return new LoadFxmlXml();
    }

    /**
     * Create an instance of {@link LoadFxmlXmlResponse }
     * 
     */
    public LoadFxmlXmlResponse createLoadFxmlXmlResponse() {
        return new LoadFxmlXmlResponse();
    }

    /**
     * Create an instance of {@link LoadJaxbJson }
     * 
     */
    public LoadJaxbJson createLoadJaxbJson() {
        return new LoadJaxbJson();
    }

    /**
     * Create an instance of {@link LoadJaxbJsonResponse }
     * 
     */
    public LoadJaxbJsonResponse createLoadJaxbJsonResponse() {
        return new LoadJaxbJsonResponse();
    }

    /**
     * Create an instance of {@link LoadJaxbXml }
     * 
     */
    public LoadJaxbXml createLoadJaxbXml() {
        return new LoadJaxbXml();
    }

    /**
     * Create an instance of {@link LoadJaxbXmlResponse }
     * 
     */
    public LoadJaxbXmlResponse createLoadJaxbXmlResponse() {
        return new LoadJaxbXmlResponse();
    }

    /**
     * Create an instance of {@link SaveFxmlJson }
     * 
     */
    public SaveFxmlJson createSaveFxmlJson() {
        return new SaveFxmlJson();
    }

    /**
     * Create an instance of {@link SaveFxmlJsonResponse }
     * 
     */
    public SaveFxmlJsonResponse createSaveFxmlJsonResponse() {
        return new SaveFxmlJsonResponse();
    }

    /**
     * Create an instance of {@link SaveFxmlXml }
     * 
     */
    public SaveFxmlXml createSaveFxmlXml() {
        return new SaveFxmlXml();
    }

    /**
     * Create an instance of {@link SaveFxmlXmlResponse }
     * 
     */
    public SaveFxmlXmlResponse createSaveFxmlXmlResponse() {
        return new SaveFxmlXmlResponse();
    }

    /**
     * Create an instance of {@link SaveJaxbJson }
     * 
     */
    public SaveJaxbJson createSaveJaxbJson() {
        return new SaveJaxbJson();
    }

    /**
     * Create an instance of {@link SaveJaxbJsonResponse }
     * 
     */
    public SaveJaxbJsonResponse createSaveJaxbJsonResponse() {
        return new SaveJaxbJsonResponse();
    }

    /**
     * Create an instance of {@link SaveJaxbXml }
     * 
     */
    public SaveJaxbXml createSaveJaxbXml() {
        return new SaveJaxbXml();
    }

    /**
     * Create an instance of {@link SaveJaxbXmlResponse }
     * 
     */
    public SaveJaxbXmlResponse createSaveJaxbXmlResponse() {
        return new SaveJaxbXmlResponse();
    }

    /**
     * Create an instance of {@link Serialize }
     * 
     */
    public Serialize createSerialize() {
        return new Serialize();
    }

    /**
     * Create an instance of {@link SerializeResponse }
     * 
     */
    public SerializeResponse createSerializeResponse() {
        return new SerializeResponse();
    }

    /**
     * Create an instance of {@link Session }
     * 
     */
    public Session createSession() {
        return new Session();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Deserialize }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.server.tmws.levin.ru/", name = "deserialize")
    public JAXBElement<Deserialize> createDeserialize(Deserialize value) {
        return new JAXBElement<Deserialize>(_Deserialize_QNAME, Deserialize.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeserializeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.server.tmws.levin.ru/", name = "deserializeResponse")
    public JAXBElement<DeserializeResponse> createDeserializeResponse(DeserializeResponse value) {
        return new JAXBElement<DeserializeResponse>(_DeserializeResponse_QNAME, DeserializeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoadFxmlJson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.server.tmws.levin.ru/", name = "loadFxmlJson")
    public JAXBElement<LoadFxmlJson> createLoadFxmlJson(LoadFxmlJson value) {
        return new JAXBElement<LoadFxmlJson>(_LoadFxmlJson_QNAME, LoadFxmlJson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoadFxmlJsonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.server.tmws.levin.ru/", name = "loadFxmlJsonResponse")
    public JAXBElement<LoadFxmlJsonResponse> createLoadFxmlJsonResponse(LoadFxmlJsonResponse value) {
        return new JAXBElement<LoadFxmlJsonResponse>(_LoadFxmlJsonResponse_QNAME, LoadFxmlJsonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoadFxmlXml }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.server.tmws.levin.ru/", name = "loadFxmlXml")
    public JAXBElement<LoadFxmlXml> createLoadFxmlXml(LoadFxmlXml value) {
        return new JAXBElement<LoadFxmlXml>(_LoadFxmlXml_QNAME, LoadFxmlXml.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoadFxmlXmlResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.server.tmws.levin.ru/", name = "loadFxmlXmlResponse")
    public JAXBElement<LoadFxmlXmlResponse> createLoadFxmlXmlResponse(LoadFxmlXmlResponse value) {
        return new JAXBElement<LoadFxmlXmlResponse>(_LoadFxmlXmlResponse_QNAME, LoadFxmlXmlResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoadJaxbJson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.server.tmws.levin.ru/", name = "loadJaxbJson")
    public JAXBElement<LoadJaxbJson> createLoadJaxbJson(LoadJaxbJson value) {
        return new JAXBElement<LoadJaxbJson>(_LoadJaxbJson_QNAME, LoadJaxbJson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoadJaxbJsonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.server.tmws.levin.ru/", name = "loadJaxbJsonResponse")
    public JAXBElement<LoadJaxbJsonResponse> createLoadJaxbJsonResponse(LoadJaxbJsonResponse value) {
        return new JAXBElement<LoadJaxbJsonResponse>(_LoadJaxbJsonResponse_QNAME, LoadJaxbJsonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoadJaxbXml }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.server.tmws.levin.ru/", name = "loadJaxbXml")
    public JAXBElement<LoadJaxbXml> createLoadJaxbXml(LoadJaxbXml value) {
        return new JAXBElement<LoadJaxbXml>(_LoadJaxbXml_QNAME, LoadJaxbXml.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoadJaxbXmlResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.server.tmws.levin.ru/", name = "loadJaxbXmlResponse")
    public JAXBElement<LoadJaxbXmlResponse> createLoadJaxbXmlResponse(LoadJaxbXmlResponse value) {
        return new JAXBElement<LoadJaxbXmlResponse>(_LoadJaxbXmlResponse_QNAME, LoadJaxbXmlResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveFxmlJson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.server.tmws.levin.ru/", name = "saveFxmlJson")
    public JAXBElement<SaveFxmlJson> createSaveFxmlJson(SaveFxmlJson value) {
        return new JAXBElement<SaveFxmlJson>(_SaveFxmlJson_QNAME, SaveFxmlJson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveFxmlJsonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.server.tmws.levin.ru/", name = "saveFxmlJsonResponse")
    public JAXBElement<SaveFxmlJsonResponse> createSaveFxmlJsonResponse(SaveFxmlJsonResponse value) {
        return new JAXBElement<SaveFxmlJsonResponse>(_SaveFxmlJsonResponse_QNAME, SaveFxmlJsonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveFxmlXml }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.server.tmws.levin.ru/", name = "saveFxmlXml")
    public JAXBElement<SaveFxmlXml> createSaveFxmlXml(SaveFxmlXml value) {
        return new JAXBElement<SaveFxmlXml>(_SaveFxmlXml_QNAME, SaveFxmlXml.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveFxmlXmlResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.server.tmws.levin.ru/", name = "saveFxmlXmlResponse")
    public JAXBElement<SaveFxmlXmlResponse> createSaveFxmlXmlResponse(SaveFxmlXmlResponse value) {
        return new JAXBElement<SaveFxmlXmlResponse>(_SaveFxmlXmlResponse_QNAME, SaveFxmlXmlResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveJaxbJson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.server.tmws.levin.ru/", name = "saveJaxbJson")
    public JAXBElement<SaveJaxbJson> createSaveJaxbJson(SaveJaxbJson value) {
        return new JAXBElement<SaveJaxbJson>(_SaveJaxbJson_QNAME, SaveJaxbJson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveJaxbJsonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.server.tmws.levin.ru/", name = "saveJaxbJsonResponse")
    public JAXBElement<SaveJaxbJsonResponse> createSaveJaxbJsonResponse(SaveJaxbJsonResponse value) {
        return new JAXBElement<SaveJaxbJsonResponse>(_SaveJaxbJsonResponse_QNAME, SaveJaxbJsonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveJaxbXml }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.server.tmws.levin.ru/", name = "saveJaxbXml")
    public JAXBElement<SaveJaxbXml> createSaveJaxbXml(SaveJaxbXml value) {
        return new JAXBElement<SaveJaxbXml>(_SaveJaxbXml_QNAME, SaveJaxbXml.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveJaxbXmlResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.server.tmws.levin.ru/", name = "saveJaxbXmlResponse")
    public JAXBElement<SaveJaxbXmlResponse> createSaveJaxbXmlResponse(SaveJaxbXmlResponse value) {
        return new JAXBElement<SaveJaxbXmlResponse>(_SaveJaxbXmlResponse_QNAME, SaveJaxbXmlResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Serialize }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.server.tmws.levin.ru/", name = "serialize")
    public JAXBElement<Serialize> createSerialize(Serialize value) {
        return new JAXBElement<Serialize>(_Serialize_QNAME, Serialize.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SerializeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.server.tmws.levin.ru/", name = "serializeResponse")
    public JAXBElement<SerializeResponse> createSerializeResponse(SerializeResponse value) {
        return new JAXBElement<SerializeResponse>(_SerializeResponse_QNAME, SerializeResponse.class, null, value);
    }

}
