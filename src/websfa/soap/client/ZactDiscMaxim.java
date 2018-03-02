
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
 *         &lt;element name="PattId" type="{urn:sap-com:document:sap:rfc:functions}char14"/&gt;
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
    "pattId"
})
@XmlRootElement(name = "ZactDiscMaxim")
public class ZactDiscMaxim {

    @XmlElement(name = "PattId", required = true)
    protected String pattId;

    /**
     * Gets the value of the pattId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPattId() {
        return pattId;
    }

    /**
     * Sets the value of the pattId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPattId(String value) {
        this.pattId = value;
    }

}
