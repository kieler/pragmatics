
package de.cau.cs.kieler.kwebs.jaxws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import de.cau.cs.kieler.kwebs.GraphLayoutOption;


/**
 * <p>Java class for graphLayout complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="graphLayout">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serializedGraph" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="format" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="options" type="{http://rtsys.informatik.uni-kiel.de/layout}graphLayoutOption" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "graphLayout", propOrder = {
    "serializedGraph",
    "format",
    "options"
})
public class GraphLayout {

    @XmlElement(required = true)
    protected String serializedGraph;
    @XmlElement(required = true)
    protected String format;
    @XmlElement(nillable = true)
    protected List<GraphLayoutOption> options;

    /**
     * Gets the value of the serializedGraph property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerializedGraph() {
        return serializedGraph;
    }

    /**
     * Sets the value of the serializedGraph property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerializedGraph(String value) {
        this.serializedGraph = value;
    }

    /**
     * Gets the value of the format property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormat() {
        return format;
    }

    /**
     * Sets the value of the format property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormat(String value) {
        this.format = value;
    }

    /**
     * Gets the value of the options property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the options property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOptions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GraphLayoutOption }
     * 
     * 
     */
    public List<GraphLayoutOption> getOptions() {
        if (options == null) {
            options = new ArrayList<GraphLayoutOption>();
        }
        return this.options;
    }

}
