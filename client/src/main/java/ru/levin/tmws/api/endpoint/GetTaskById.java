
package ru.levin.tmws.api.endpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getTaskById complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getTaskById"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ticket" type="{http://endpoint.api.tmws.levin.ru/}sessionDTO" minOccurs="0"/&gt;
 *         &lt;element name="taskId" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getTaskById", propOrder = {
    "ticket",
    "taskId"
})
public class GetTaskById {

    protected SessionDTO ticket;
    protected int taskId;

    /**
     * Gets the value of the ticket property.
     * 
     * @return
     *     possible object is
     *     {@link SessionDTO }
     *     
     */
    public SessionDTO getTicket() {
        return ticket;
    }

    /**
     * Sets the value of the ticket property.
     * 
     * @param value
     *     allowed object is
     *     {@link SessionDTO }
     *     
     */
    public void setTicket(SessionDTO value) {
        this.ticket = value;
    }

    /**
     * Gets the value of the taskId property.
     * 
     */
    public int getTaskId() {
        return taskId;
    }

    /**
     * Sets the value of the taskId property.
     * 
     */
    public void setTaskId(int value) {
        this.taskId = value;
    }

}
