
package ru.levin.tmws.api.endpoint;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for roleType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="roleType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="ADMIN"/&gt;
 *     &lt;enumeration value="USER"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "roleType")
@XmlEnum
public enum RoleType {

    ADMIN,
    USER;

    public String value() {
        return name();
    }

    public static RoleType fromValue(String v) {
        return valueOf(v);
    }

}
