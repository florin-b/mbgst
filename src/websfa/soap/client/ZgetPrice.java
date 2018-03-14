
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
 *         &lt;element name="Canal" type="{urn:sap-com:document:sap:rfc:functions}char2"/&gt;
 *         &lt;element name="City" type="{urn:sap-com:document:sap:soap:functions:mc-style}char25"/&gt;
 *         &lt;element name="Dzterm" type="{urn:sap-com:document:sap:soap:functions:mc-style}char4"/&gt;
 *         &lt;element name="GvCant" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3" minOccurs="0"/&gt;
 *         &lt;element name="GvKunnr" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="GvLgort" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
 *         &lt;element name="GvMatnr" type="{urn:sap-com:document:sap:rfc:functions}char18"/&gt;
 *         &lt;element name="GvSite" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="GvSpart" type="{urn:sap-com:document:sap:rfc:functions}char2"/&gt;
 *         &lt;element name="GvVrkme" type="{urn:sap-com:document:sap:rfc:functions}unit3" minOccurs="0"/&gt;
 *         &lt;element name="GvWerks" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
 *         &lt;element name="Mp" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
 *         &lt;element name="Regio" type="{urn:sap-com:document:sap:rfc:functions}char3"/&gt;
 *         &lt;element name="TipPers" type="{urn:sap-com:document:sap:soap:functions:mc-style}char3"/&gt;
 *         &lt;element name="UlStoc" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
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
    "canal",
    "city",
    "dzterm",
    "gvCant",
    "gvKunnr",
    "gvLgort",
    "gvMatnr",
    "gvSite",
    "gvSpart",
    "gvVrkme",
    "gvWerks",
    "mp",
    "regio",
    "tipPers",
    "ulStoc"
})
@XmlRootElement(name = "ZgetPrice")
public class ZgetPrice {

    @XmlElement(name = "Canal", required = true)
    protected String canal;
    @XmlElement(name = "City", required = true)
    protected String city;
    @XmlElement(name = "Dzterm", required = true)
    protected String dzterm;
    @XmlElement(name = "GvCant")
    protected BigDecimal gvCant;
    @XmlElement(name = "GvKunnr", required = true)
    protected String gvKunnr;
    @XmlElement(name = "GvLgort", required = true)
    protected String gvLgort;
    @XmlElement(name = "GvMatnr", required = true)
    protected String gvMatnr;
    @XmlElement(name = "GvSite", required = true)
    protected String gvSite;
    @XmlElement(name = "GvSpart", required = true)
    protected String gvSpart;
    @XmlElement(name = "GvVrkme")
    protected String gvVrkme;
    @XmlElement(name = "GvWerks", required = true)
    protected String gvWerks;
    @XmlElement(name = "Mp", required = true)
    protected String mp;
    @XmlElement(name = "Regio", required = true)
    protected String regio;
    @XmlElement(name = "TipPers", required = true)
    protected String tipPers;
    @XmlElement(name = "UlStoc", required = true)
    protected String ulStoc;

    /**
     * Gets the value of the canal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanal() {
        return canal;
    }

    /**
     * Sets the value of the canal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanal(String value) {
        this.canal = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the dzterm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDzterm() {
        return dzterm;
    }

    /**
     * Sets the value of the dzterm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDzterm(String value) {
        this.dzterm = value;
    }

    /**
     * Gets the value of the gvCant property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGvCant() {
        return gvCant;
    }

    /**
     * Sets the value of the gvCant property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGvCant(BigDecimal value) {
        this.gvCant = value;
    }

    /**
     * Gets the value of the gvKunnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGvKunnr() {
        return gvKunnr;
    }

    /**
     * Sets the value of the gvKunnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGvKunnr(String value) {
        this.gvKunnr = value;
    }

    /**
     * Gets the value of the gvLgort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGvLgort() {
        return gvLgort;
    }

    /**
     * Sets the value of the gvLgort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGvLgort(String value) {
        this.gvLgort = value;
    }

    /**
     * Gets the value of the gvMatnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGvMatnr() {
        return gvMatnr;
    }

    /**
     * Sets the value of the gvMatnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGvMatnr(String value) {
        this.gvMatnr = value;
    }

    /**
     * Gets the value of the gvSite property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGvSite() {
        return gvSite;
    }

    /**
     * Sets the value of the gvSite property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGvSite(String value) {
        this.gvSite = value;
    }

    /**
     * Gets the value of the gvSpart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGvSpart() {
        return gvSpart;
    }

    /**
     * Sets the value of the gvSpart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGvSpart(String value) {
        this.gvSpart = value;
    }

    /**
     * Gets the value of the gvVrkme property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGvVrkme() {
        return gvVrkme;
    }

    /**
     * Sets the value of the gvVrkme property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGvVrkme(String value) {
        this.gvVrkme = value;
    }

    /**
     * Gets the value of the gvWerks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGvWerks() {
        return gvWerks;
    }

    /**
     * Sets the value of the gvWerks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGvWerks(String value) {
        this.gvWerks = value;
    }

    /**
     * Gets the value of the mp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMp() {
        return mp;
    }

    /**
     * Sets the value of the mp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMp(String value) {
        this.mp = value;
    }

    /**
     * Gets the value of the regio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegio() {
        return regio;
    }

    /**
     * Sets the value of the regio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegio(String value) {
        this.regio = value;
    }

    /**
     * Gets the value of the tipPers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipPers() {
        return tipPers;
    }

    /**
     * Sets the value of the tipPers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipPers(String value) {
        this.tipPers = value;
    }

    /**
     * Gets the value of the ulStoc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUlStoc() {
        return ulStoc;
    }

    /**
     * Sets the value of the ulStoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUlStoc(String value) {
        this.ulStoc = value;
    }

}
