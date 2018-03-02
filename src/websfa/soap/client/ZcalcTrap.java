
package websfa.soap.client;

import java.math.BigDecimal;
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
 *         &lt;element name="VCity" type="{urn:sap-com:document:sap:rfc:functions}char25" minOccurs="0"/&gt;
 *         &lt;element name="VKunnr" type="{urn:sap-com:document:sap:rfc:functions}char10" minOccurs="0"/&gt;
 *         &lt;element name="VNetwr" type="{urn:sap-com:document:sap:rfc:functions}curr15.2" minOccurs="0"/&gt;
 *         &lt;element name="VRegio" type="{urn:sap-com:document:sap:rfc:functions}char3" minOccurs="0"/&gt;
 *         &lt;element name="VUl" type="{urn:sap-com:document:sap:rfc:functions}char4" minOccurs="0"/&gt;
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
    "vCity",
    "vKunnr",
    "vNetwr",
    "vRegio",
    "vUl"
})
@XmlRootElement(name = "ZcalcTrap")
public class ZcalcTrap {

    @XmlElement(name = "VCity")
    protected String vCity;
    @XmlElement(name = "VKunnr")
    protected String vKunnr;
    @XmlElement(name = "VNetwr")
    protected BigDecimal vNetwr;
    @XmlElement(name = "VRegio")
    protected String vRegio;
    @XmlElement(name = "VUl")
    protected String vUl;

    /**
     * Gets the value of the vCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVCity() {
        return vCity;
    }

    /**
     * Sets the value of the vCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVCity(String value) {
        this.vCity = value;
    }

    /**
     * Gets the value of the vKunnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVKunnr() {
        return vKunnr;
    }

    /**
     * Sets the value of the vKunnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVKunnr(String value) {
        this.vKunnr = value;
    }

    /**
     * Gets the value of the vNetwr property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVNetwr() {
        return vNetwr;
    }

    /**
     * Sets the value of the vNetwr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVNetwr(BigDecimal value) {
        this.vNetwr = value;
    }

    /**
     * Gets the value of the vRegio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVRegio() {
        return vRegio;
    }

    /**
     * Sets the value of the vRegio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVRegio(String value) {
        this.vRegio = value;
    }

    /**
     * Gets the value of the vUl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVUl() {
        return vUl;
    }

    /**
     * Sets the value of the vUl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVUl(String value) {
        this.vUl = value;
    }

}
