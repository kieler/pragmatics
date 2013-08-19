
package de.cau.cs.kieler.kwebs.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getPreviewImage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getPreviewImage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="previewImage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPreviewImage", propOrder = {
    "previewImage"
})
public class GetPreviewImage {

    @XmlElement(required = true)
    protected String previewImage;

    /**
     * Gets the value of the previewImage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreviewImage() {
        return previewImage;
    }

    /**
     * Sets the value of the previewImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreviewImage(String value) {
        this.previewImage = value;
    }

}
