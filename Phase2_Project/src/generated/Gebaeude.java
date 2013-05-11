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
 *         &lt;element ref="{}GName"/>
 *         &lt;element ref="{}GAnschrift"/>
 *         &lt;element ref="{}VeranstaltungIDRef" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}EquipmentM"/>
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
    "gName",
    "gAnschrift",
    "veranstaltungIDRef",
    "equipmentM"
})
@XmlRootElement(name = "Gebaeude")
public class Gebaeude {

    @XmlElement(name = "GName", required = true)
    protected String gName;
    @XmlElement(name = "GAnschrift", required = true)
    protected String gAnschrift;
    @XmlElement(name = "VeranstaltungIDRef")
    protected List<Object> veranstaltungIDRef;
    @XmlElement(name = "EquipmentM", required = true)
    protected EquipmentM equipmentM;
    @XmlAttribute(required = true)
    protected String id;

    /**
     * Gets the value of the gName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGName() {
        return gName;
    }

    /**
     * Sets the value of the gName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGName(String value) {
        this.gName = value;
    }

    /**
     * Gets the value of the gAnschrift property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGAnschrift() {
        return gAnschrift;
    }

    /**
     * Sets the value of the gAnschrift property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGAnschrift(String value) {
        this.gAnschrift = value;
    }

    /**
     * Gets the value of the veranstaltungIDRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the veranstaltungIDRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVeranstaltungIDRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getVeranstaltungIDRef() {
        if (veranstaltungIDRef == null) {
            veranstaltungIDRef = new ArrayList<Object>();
        }
        return this.veranstaltungIDRef;
    }

    /**
     * Gets the value of the equipmentM property.
     * 
     * @return
     *     possible object is
     *     {@link EquipmentM }
     *     
     */
    public EquipmentM getEquipmentM() {
        return equipmentM;
    }

    /**
     * Sets the value of the equipmentM property.
     * 
     * @param value
     *     allowed object is
     *     {@link EquipmentM }
     *     
     */
    public void setEquipmentM(EquipmentM value) {
        this.equipmentM = value;
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
