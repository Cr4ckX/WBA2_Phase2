//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.08 at 01:40:15 PM MESZ 
//


package generated;

import java.util.ArrayList;
import java.util.List;
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
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}SportgruppenM" maxOccurs="unbounded"/>
 *         &lt;element ref="{}OrteM" maxOccurs="unbounded"/>
 *         &lt;element ref="{}VeranstalterM" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sportgruppenM",
    "orteM",
    "veranstalterM"
})
@XmlRootElement(name = "Sportverzeichnis")
public class Sportverzeichnis {

    @XmlElement(name = "SportgruppenM", required = true)
    protected List<SportgruppenM> sportgruppenM;
    @XmlElement(name = "OrteM", required = true)
    protected List<OrteM> orteM;
    @XmlElement(name = "VeranstalterM", required = true)
    protected List<VeranstalterM> veranstalterM;

    /**
     * Gets the value of the sportgruppenM property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sportgruppenM property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSportgruppenM().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SportgruppenM }
     * 
     * 
     */
    public List<SportgruppenM> getSportgruppenM() {
        if (sportgruppenM == null) {
            sportgruppenM = new ArrayList<SportgruppenM>();
        }
        return this.sportgruppenM;
    }

    /**
     * Gets the value of the orteM property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orteM property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrteM().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrteM }
     * 
     * 
     */
    public List<OrteM> getOrteM() {
        if (orteM == null) {
            orteM = new ArrayList<OrteM>();
        }
        return this.orteM;
    }

    /**
     * Gets the value of the veranstalterM property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the veranstalterM property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVeranstalterM().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VeranstalterM }
     * 
     * 
     */
    public List<VeranstalterM> getVeranstalterM() {
        if (veranstalterM == null) {
            veranstalterM = new ArrayList<VeranstalterM>();
        }
        return this.veranstalterM;
    }

}