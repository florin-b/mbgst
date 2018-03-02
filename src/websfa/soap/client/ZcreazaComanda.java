
package websfa.soap.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GvEvent" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="Id" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "gvEvent",
    "id"
})
@XmlRootElement(name = "ZcreazaComanda")
public class ZcreazaComanda {

    @XmlElement(name = "GvEvent", required = true)
    protected String gvEvent;
    @XmlElement(name = "Id", required = true)
    protected String id;

    /**
     * Gets the value of the gvEvent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGvEvent() {
        return gvEvent;
    }

    /**
     * Sets the value of the gvEvent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGvEvent(String value) {
        this.gvEvent = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
