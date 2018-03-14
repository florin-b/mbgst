
package websfa.soap.client;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="ErrorCode" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/&gt;
 *         &lt;element name="GvCant" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/&gt;
 *         &lt;element name="GvCantFree" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/&gt;
 *         &lt;element name="GvCond" type="{urn:sap-com:document:sap:rfc:functions}char200"/&gt;
 *         &lt;element name="GvCurrency" type="{urn:sap-com:document:sap:rfc:functions}cuky5"/&gt;
 *         &lt;element name="GvMatnrFree" type="{urn:sap-com:document:sap:rfc:functions}char18"/&gt;
 *         &lt;element name="GvNetwr" type="{urn:sap-com:document:sap:rfc:functions}curr15.2"/&gt;
 *         &lt;element name="GvNetwrFree" type="{urn:sap-com:document:sap:rfc:functions}curr15.2"/&gt;
 *         &lt;element name="GvNetwrList" type="{urn:sap-com:document:sap:rfc:functions}curr15.2"/&gt;
 *         &lt;element name="GvNoDisc" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="GvVrkme" type="{urn:sap-com:document:sap:rfc:functions}unit3"/&gt;
 *         &lt;element name="GvVrkmeFree" type="{urn:sap-com:document:sap:rfc:functions}unit3"/&gt;
 *         &lt;element name="Impachet" type="{urn:sap-com:document:sap:soap:functions:mc-style}string"/&gt;
 *         &lt;element name="Multiplu" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/&gt;
 *         &lt;element name="OutCantUmb" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/&gt;
 *         &lt;element name="OutUmb" type="{urn:sap-com:document:sap:rfc:functions}unit3"/&gt;
 *         &lt;element name="ProcTrap" type="{urn:sap-com:document:sap:soap:functions:mc-style}decimal5.2"/&gt;
 *         &lt;element name="VMess" type="{urn:sap-com:document:sap:soap:functions:mc-style}char100"/&gt;
 *         &lt;element name="ValTrap" type="{urn:sap-com:document:sap:rfc:functions}curr15.2"/&gt;
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
    "errorCode",
    "gvCant",
    "gvCantFree",
    "gvCond",
    "gvCurrency",
    "gvMatnrFree",
    "gvNetwr",
    "gvNetwrFree",
    "gvNetwrList",
    "gvNoDisc",
    "gvVrkme",
    "gvVrkmeFree",
    "impachet",
    "multiplu",
    "outCantUmb",
    "outUmb",
    "procTrap",
    "vMess",
    "valTrap"
})
@XmlRootElement(name = "ZgetPriceResponse")
public class ZgetPriceResponse {

    @XmlElement(name = "ErrorCode")
    @XmlSchemaType(name = "unsignedByte")
    protected short errorCode;
    @XmlElement(name = "GvCant", required = true)
    protected BigDecimal gvCant;
    @XmlElement(name = "GvCantFree", required = true)
    protected BigDecimal gvCantFree;
    @XmlElement(name = "GvCond", required = true)
    protected String gvCond;
    @XmlElement(name = "GvCurrency", required = true)
    protected String gvCurrency;
    @XmlElement(name = "GvMatnrFree", required = true)
    protected String gvMatnrFree;
    @XmlElement(name = "GvNetwr", required = true)
    protected BigDecimal gvNetwr;
    @XmlElement(name = "GvNetwrFree", required = true)
    protected BigDecimal gvNetwrFree;
    @XmlElement(name = "GvNetwrList", required = true)
    protected BigDecimal gvNetwrList;
    @XmlElement(name = "GvNoDisc", required = true)
    protected String gvNoDisc;
    @XmlElement(name = "GvVrkme", required = true)
    protected String gvVrkme;
    @XmlElement(name = "GvVrkmeFree", required = true)
    protected String gvVrkmeFree;
    @XmlElement(name = "Impachet", required = true)
    protected String impachet;
    @XmlElement(name = "Multiplu", required = true)
    protected BigDecimal multiplu;
    @XmlElement(name = "OutCantUmb", required = true)
    protected BigDecimal outCantUmb;
    @XmlElement(name = "OutUmb", required = true)
    protected String outUmb;
    @XmlElement(name = "ProcTrap", required = true)
    protected BigDecimal procTrap;
    @XmlElement(name = "VMess", required = true)
    protected String vMess;
    @XmlElement(name = "ValTrap", required = true)
    protected BigDecimal valTrap;

    /**
     * Gets the value of the errorCode property.
     * 
     */
    public short getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     * 
     */
    public void setErrorCode(short value) {
        this.errorCode = value;
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
     * Gets the value of the gvCantFree property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGvCantFree() {
        return gvCantFree;
    }

    /**
     * Sets the value of the gvCantFree property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGvCantFree(BigDecimal value) {
        this.gvCantFree = value;
    }

    /**
     * Gets the value of the gvCond property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGvCond() {
        return gvCond;
    }

    /**
     * Sets the value of the gvCond property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGvCond(String value) {
        this.gvCond = value;
    }

    /**
     * Gets the value of the gvCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGvCurrency() {
        return gvCurrency;
    }

    /**
     * Sets the value of the gvCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGvCurrency(String value) {
        this.gvCurrency = value;
    }

    /**
     * Gets the value of the gvMatnrFree property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGvMatnrFree() {
        return gvMatnrFree;
    }

    /**
     * Sets the value of the gvMatnrFree property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGvMatnrFree(String value) {
        this.gvMatnrFree = value;
    }

    /**
     * Gets the value of the gvNetwr property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGvNetwr() {
        return gvNetwr;
    }

    /**
     * Sets the value of the gvNetwr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGvNetwr(BigDecimal value) {
        this.gvNetwr = value;
    }

    /**
     * Gets the value of the gvNetwrFree property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGvNetwrFree() {
        return gvNetwrFree;
    }

    /**
     * Sets the value of the gvNetwrFree property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGvNetwrFree(BigDecimal value) {
        this.gvNetwrFree = value;
    }

    /**
     * Gets the value of the gvNetwrList property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGvNetwrList() {
        return gvNetwrList;
    }

    /**
     * Sets the value of the gvNetwrList property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGvNetwrList(BigDecimal value) {
        this.gvNetwrList = value;
    }

    /**
     * Gets the value of the gvNoDisc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGvNoDisc() {
        return gvNoDisc;
    }

    /**
     * Sets the value of the gvNoDisc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGvNoDisc(String value) {
        this.gvNoDisc = value;
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
     * Gets the value of the gvVrkmeFree property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGvVrkmeFree() {
        return gvVrkmeFree;
    }

    /**
     * Sets the value of the gvVrkmeFree property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGvVrkmeFree(String value) {
        this.gvVrkmeFree = value;
    }

    /**
     * Gets the value of the impachet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImpachet() {
        return impachet;
    }

    /**
     * Sets the value of the impachet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImpachet(String value) {
        this.impachet = value;
    }

    /**
     * Gets the value of the multiplu property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMultiplu() {
        return multiplu;
    }

    /**
     * Sets the value of the multiplu property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMultiplu(BigDecimal value) {
        this.multiplu = value;
    }

    /**
     * Gets the value of the outCantUmb property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOutCantUmb() {
        return outCantUmb;
    }

    /**
     * Sets the value of the outCantUmb property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOutCantUmb(BigDecimal value) {
        this.outCantUmb = value;
    }

    /**
     * Gets the value of the outUmb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutUmb() {
        return outUmb;
    }

    /**
     * Sets the value of the outUmb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutUmb(String value) {
        this.outUmb = value;
    }

    /**
     * Gets the value of the procTrap property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getProcTrap() {
        return procTrap;
    }

    /**
     * Sets the value of the procTrap property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setProcTrap(BigDecimal value) {
        this.procTrap = value;
    }

    /**
     * Gets the value of the vMess property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVMess() {
        return vMess;
    }

    /**
     * Sets the value of the vMess property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVMess(String value) {
        this.vMess = value;
    }

    /**
     * Gets the value of the valTrap property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValTrap() {
        return valTrap;
    }

    /**
     * Sets the value of the valTrap property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValTrap(BigDecimal value) {
        this.valTrap = value;
    }

}
