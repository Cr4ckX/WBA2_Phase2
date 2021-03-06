//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.28 at 11:36:45 PM MESZ 
//


package generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}SName"/>
 *         &lt;element ref="{}SBeschreibung"/>
 *         &lt;element ref="{}SHerkunft"/>
 *         &lt;element ref="{}SVorraussetzung"/>
 *         &lt;element ref="{}SRegeln"/>
 *         &lt;element ref="{}VeranstaltungenM"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{}id use="required""/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sName",
    "sBeschreibung",
    "sHerkunft",
    "sVorraussetzung",
    "sRegeln",
    "veranstaltungenM"
})
@XmlRootElement(name = "Sportart")
public class Sportart {

    @XmlElement(name = "SName", required = true)
    protected String sName;
    @XmlElement(name = "SBeschreibung", required = true)
    protected String sBeschreibung;
    @XmlElement(name = "SHerkunft", required = true)
    protected String sHerkunft;
    @XmlElement(name = "SVorraussetzung", required = true)
    protected String sVorraussetzung;
    @XmlElement(name = "SRegeln", required = true)
    protected String sRegeln;
    @XmlElement(name = "VeranstaltungenM", required = true)
    protected VeranstaltungenM veranstaltungenM;
    @XmlAttribute(required = true)
    protected String id;

    /**
     * Gets the value of the sName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSName() {
        return sName;
    }

    /**
     * Sets the value of the sName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSName(String value) {
        this.sName = value;
    }

    /**
     * Gets the value of the sBeschreibung property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSBeschreibung() {
        return sBeschreibung;
    }

    /**
     * Sets the value of the sBeschreibung property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSBeschreibung(String value) {
        this.sBeschreibung = value;
    }

    /**
     * Gets the value of the sHerkunft property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSHerkunft() {
        return sHerkunft;
    }

    /**
     * Sets the value of the sHerkunft property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSHerkunft(String value) {
        this.sHerkunft = value;
    }

    /**
     * Gets the value of the sVorraussetzung property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSVorraussetzung() {
        return sVorraussetzung;
    }

    /**
     * Sets the value of the sVorraussetzung property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSVorraussetzung(String value) {
        this.sVorraussetzung = value;
    }

    /**
     * Gets the value of the sRegeln property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSRegeln() {
        return sRegeln;
    }

    /**
     * Sets the value of the sRegeln property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSRegeln(String value) {
        this.sRegeln = value;
    }

    /**
     * Gets the value of the veranstaltungenM property.
     * 
     * @return
     *     possible object is
     *     {@link VeranstaltungenM }
     *     
     */
    public VeranstaltungenM getVeranstaltungenM() {
        return veranstaltungenM;
    }

    /**
     * Sets the value of the veranstaltungenM property.
     * 
     * @param value
     *     allowed object is
     *     {@link VeranstaltungenM }
     *     
     */
    public void setVeranstaltungenM(VeranstaltungenM value) {
        this.veranstaltungenM = value;
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
