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
 *         &lt;element ref="{}OName"/>
 *         &lt;element ref="{}GebaeudeIDRef" maxOccurs="unbounded"/>
 *         &lt;element ref="{}OrtMParent"/>
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
    "gebaeudeIDRef",
    "ortMParent"
})
@XmlRootElement(name = "Ort")
public class Ort {

    @XmlElement(name = "OName", required = true)
    protected String oName;
    @XmlElement(name = "GebaeudeIDRef", required = true)
    protected List<Object> gebaeudeIDRef;
    @XmlElement(name = "OrtMParent", required = true)
    protected Object ortMParent;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
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
     * Gets the value of the gebaeudeIDRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gebaeudeIDRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGebaeudeIDRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getGebaeudeIDRef() {
        if (gebaeudeIDRef == null) {
            gebaeudeIDRef = new ArrayList<Object>();
        }
        return this.gebaeudeIDRef;
    }

    /**
     * Gets the value of the ortMParent property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getOrtMParent() {
        return ortMParent;
    }

    /**
     * Sets the value of the ortMParent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setOrtMParent(Object value) {
        this.ortMParent = value;
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
