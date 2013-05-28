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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element ref="{}VBeschreibung"/>
 *         &lt;element ref="{}VInfo" minOccurs="0"/>
 *         &lt;element ref="{}VDatum"/>
 *         &lt;element ref="{}VUhrzeit"/>
 *         &lt;element ref="{}VNiveau" minOccurs="0"/>
 *         &lt;element ref="{}VVorraussetzungen" minOccurs="0"/>
 *         &lt;element ref="{}GebaeudeIDRef"/>
 *         &lt;element ref="{}VeranstalterIDRef"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{}id use="required""/>
 *       &lt;attribute ref="{}deleted use="required""/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "vBeschreibung",
    "vInfo",
    "vDatum",
    "vUhrzeit",
    "vNiveau",
    "vVorraussetzungen",
    "gebaeudeIDRef",
    "veranstalterIDRef"
})
@XmlRootElement(name = "Veranstaltung")
public class Veranstaltung {

    @XmlElement(name = "VBeschreibung", required = true)
    protected String vBeschreibung;
    @XmlElement(name = "VInfo")
    protected String vInfo;
    @XmlElement(name = "VDatum", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar vDatum;
    @XmlElement(name = "VUhrzeit", required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar vUhrzeit;
    @XmlElement(name = "VNiveau")
    protected String vNiveau;
    @XmlElement(name = "VVorraussetzungen")
    protected String vVorraussetzungen;
    @XmlElement(name = "GebaeudeIDRef", required = true)
    protected Object gebaeudeIDRef;
    @XmlElement(name = "VeranstalterIDRef", required = true)
    protected Object veranstalterIDRef;
    @XmlAttribute(required = true)
    protected String id;
    @XmlAttribute(required = true)
    protected boolean deleted;

    /**
     * Gets the value of the vBeschreibung property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVBeschreibung() {
        return vBeschreibung;
    }

    /**
     * Sets the value of the vBeschreibung property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVBeschreibung(String value) {
        this.vBeschreibung = value;
    }

    /**
     * Gets the value of the vInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVInfo() {
        return vInfo;
    }

    /**
     * Sets the value of the vInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVInfo(String value) {
        this.vInfo = value;
    }

    /**
     * Gets the value of the vDatum property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getVDatum() {
        return vDatum;
    }

    /**
     * Sets the value of the vDatum property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setVDatum(XMLGregorianCalendar value) {
        this.vDatum = value;
    }

    /**
     * Gets the value of the vUhrzeit property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getVUhrzeit() {
        return vUhrzeit;
    }

    /**
     * Sets the value of the vUhrzeit property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setVUhrzeit(XMLGregorianCalendar value) {
        this.vUhrzeit = value;
    }

    /**
     * Gets the value of the vNiveau property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVNiveau() {
        return vNiveau;
    }

    /**
     * Sets the value of the vNiveau property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVNiveau(String value) {
        this.vNiveau = value;
    }

    /**
     * Gets the value of the vVorraussetzungen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVVorraussetzungen() {
        return vVorraussetzungen;
    }

    /**
     * Sets the value of the vVorraussetzungen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVVorraussetzungen(String value) {
        this.vVorraussetzungen = value;
    }

    /**
     * Gets the value of the gebaeudeIDRef property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getGebaeudeIDRef() {
        return gebaeudeIDRef;
    }

    /**
     * Sets the value of the gebaeudeIDRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setGebaeudeIDRef(Object value) {
        this.gebaeudeIDRef = value;
    }

    /**
     * Gets the value of the veranstalterIDRef property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getVeranstalterIDRef() {
        return veranstalterIDRef;
    }

    /**
     * Sets the value of the veranstalterIDRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setVeranstalterIDRef(Object value) {
        this.veranstalterIDRef = value;
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

    /**
     * Gets the value of the deleted property.
     * 
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Sets the value of the deleted property.
     * 
     */
    public void setDeleted(boolean value) {
        this.deleted = value;
    }

}
