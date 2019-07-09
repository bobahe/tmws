
package ru.levin.tmws.server.api.endpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getProjectByNameOrDescription complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getProjectByNameOrDescription"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ticket" type="{http://endpoint.api.server.tmws.levin.ru/}session" minOccurs="0"/&gt;
 *         &lt;element name="matcher" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getProjectByNameOrDescription", propOrder = {
    "ticket",
    "matcher"
})
public class GetProjectByNameOrDescription {

    protected Session ticket;
    protected String matcher;

    /**
     * Gets the value of the ticket property.
     * 
     * @return
     *     possible object is
     *     {@link Session }
     *     
     */
    public Session getTicket() {
        return ticket;
    }

    /**
     * Sets the value of the ticket property.
     * 
     * @param value
     *     allowed object is
     *     {@link Session }
     *     
     */
    public void setTicket(Session value) {
        this.ticket = value;
    }

    /**
     * Gets the value of the matcher property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatcher() {
        return matcher;
    }

    /**
     * Sets the value of the matcher property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatcher(String value) {
        this.matcher = value;
    }

}
