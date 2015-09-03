
package ivelate.p1.soapserver;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para priority.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="priority">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="VERY_HIGH"/>
 *     &lt;enumeration value="HIGH"/>
 *     &lt;enumeration value="MEDIUM"/>
 *     &lt;enumeration value="LOW"/>
 *     &lt;enumeration value="VERY_LOW"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "priority")
@XmlEnum
public enum Priority {

    VERY_HIGH,
    HIGH,
    MEDIUM,
    LOW,
    VERY_LOW;

    public String value() {
        return name();
    }

    public static Priority fromValue(String v) {
        return valueOf(v);
    }

}
