//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.11 at 08:47:10 PM MESZ 
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
 *         &lt;element ref="{}OName"/>
 *         &lt;element ref="{}GebaeudeM"/>
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
    "oName",
    "gebaeudeM"
})
@XmlRootElement(name = "Ort")
public class Ort {

    @XmlElement(name = "OName", required = true)
    protected String oName;
    @XmlElement(name = "GebaeudeM", required = true)
    protected GebaeudeM gebaeudeM;
    @XmlAttribute(required = true)
    protected String id;

    /**
     * Gets the value of the oName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOName() {
        return oName;
    }

    /**
     * Sets the value of the oName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOName(String value) {
        this.oName = value;
    }

    /**
     * Gets the value of the gebaeudeM property.
     * 
     * @return
     *     possible object is
     *     {@link GebaeudeM }
     *     
     */
    public GebaeudeM getGebaeudeM() {
        return gebaeudeM;
    }

    /**
     * Sets the value of the gebaeudeM property.
     * 
     * @param value
     *     allowed object is
     *     {@link GebaeudeM }
     *     
     */
    public void setGebaeudeM(GebaeudeM value) {
        this.gebaeudeM = value;
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
