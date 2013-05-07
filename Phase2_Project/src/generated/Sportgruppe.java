//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.07 at 09:36:40 PM MESZ 
//


package generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element ref="{}SportartIDRef" maxOccurs="unbounded"/>
 *         &lt;element ref="{}SportgruppeMParent"/>
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
    "sportartIDRef",
    "sportgruppeMParent"
})
@XmlRootElement(name = "Sportgruppe")
public class Sportgruppe {

    @XmlElement(name = "SGName", required = true)
    protected String sgName;
    @XmlElement(name = "SGBeschreibung", required = true)
    protected String sgBeschreibung;
    @XmlElement(name = "SportartIDRef", required = true)
    protected List<Object> sportartIDRef;
    @XmlElement(name = "SportgruppeMParent", required = true)
    protected Object sportgruppeMParent;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
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
     * Gets the value of the sportartIDRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sportartIDRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSportartIDRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getSportartIDRef() {
        if (sportartIDRef == null) {
            sportartIDRef = new ArrayList<Object>();
        }
        return this.sportartIDRef;
    }

    /**
     * Gets the value of the sportgruppeMParent property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getSportgruppeMParent() {
        return sportgruppeMParent;
    }

    /**
     * Sets the value of the sportgruppeMParent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setSportgruppeMParent(Object value) {
        this.sportgruppeMParent = value;
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
