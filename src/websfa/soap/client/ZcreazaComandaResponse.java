
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
 *         &lt;element name="VMesaj" type="{urn:sap-com:document:sap:soap:functions:mc-style}char100"/&gt;
 *         &lt;element name="VOk" type="{urn:sap-com:document:sap:rfc:functions}char2"/&gt;
 *         &lt;element name="VTrans" type="{urn:sap-com:document:sap:rfc:functions}curr15.2"/&gt;
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
    "vMesaj",
    "vOk",
    "vTrans"
})
@XmlRootElement(name = "ZcreazaComandaResponse")
public class ZcreazaComandaResponse {

    @XmlElement(name = "VMesaj", required = true)
    protected String vMesaj;
    @XmlElement(name = "VOk", required = true)
    protected String vOk;
    @XmlElement(name = "VTrans", required = true)
    protected BigDecimal vTrans;

    /**
     * Gets the value of the vMesaj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVMesaj() {
        return vMesaj;
    }

    /**
     * Sets the value of the vMesaj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVMesaj(String value) {
        this.vMesaj = value;
    }

    /**
     * Gets the value of the vOk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVOk() {
        return vOk;
    }

    /**
     * Sets the value of the vOk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVOk(String value) {
        this.vOk = value;
    }

    /**
     * Gets the value of the vTrans property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVTrans() {
        return vTrans;
    }

    /**
     * Sets the value of the vTrans property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVTrans(BigDecimal value) {
        this.vTrans = value;
    }

}
