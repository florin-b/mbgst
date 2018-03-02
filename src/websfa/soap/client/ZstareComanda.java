
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
 *         &lt;element name="NrCom" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="PernrCh" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="Stare" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
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
    "nrCom",
    "pernrCh",
    "stare"
})
@XmlRootElement(name = "ZstareComanda")
public class ZstareComanda {

    @XmlElement(name = "NrCom", required = true)
    protected String nrCom;
    @XmlElement(name = "PernrCh", required = true)
    protected String pernrCh;
    @XmlElement(name = "Stare", required = true)
    protected String stare;

    /**
     * Gets the value of the nrCom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNrCom() {
        return nrCom;
    }

    /**
     * Sets the value of the nrCom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNrCom(String value) {
        this.nrCom = value;
    }

    /**
     * Gets the value of the pernrCh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPernrCh() {
        return pernrCh;
    }

    /**
     * Sets the value of the pernrCh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPernrCh(String value) {
        this.pernrCh = value;
    }

    /**
     * Gets the value of the stare property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStare() {
        return stare;
    }

    /**
     * Sets the value of the stare property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStare(String value) {
        this.stare = value;
    }

}
