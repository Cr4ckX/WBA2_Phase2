//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.11 at 06:53:21 PM MESZ 
//


package generated;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{}SGName"/>
 *         &lt;element ref="{}SGBeschreibung"/>
 *         &lt;element ref="{}SportartenM" maxOccurs="unbounded"/>
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
    "sgName",
    "sgBeschreibung",
    "sportartenM"
})
@XmlRootElement(name = "Sportgruppe")
public class Sportgruppe {

    @XmlElement(name = "SGName", required = true)
    protected String sgName;
    @XmlElement(name = "SGBeschreibung", required = true)
    protected String sgBeschreibung;
    @XmlElement(name = "SportartenM", required = true)
    protected List<SportartenM> sportartenM;
    @XmlAttribute(required = true)
    protected String id;

    /**
     * Gets the value of the sgName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSGName() {
        return sgName;
    }

    /**
     * Sets the value of the sgName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSGName(String value) {
        this.sgName = value;
    }

    /**
     * Gets the value of the sgBeschreibung property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSGBeschreibung() {
        return sgBeschreibung;
    }

    /**
     * Sets the value of the sgBeschreibung property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSGBeschreibung(String value) {
        this.sgBeschreibung = value;
    }

    /**
     * Gets the value of the sportartenM property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sportartenM property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSportartenM().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SportartenM }
     * 
     * 
     */
    public List<SportartenM> getSportartenM() {
        if (sportartenM == null) {
            sportartenM = new ArrayList<SportartenM>();
        }
        return this.sportartenM;
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
