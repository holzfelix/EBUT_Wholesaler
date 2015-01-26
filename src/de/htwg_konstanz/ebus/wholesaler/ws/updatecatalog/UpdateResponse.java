
package de.htwg_konstanz.ebus.wholesaler.ws.updatecatalog;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="updateDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="listOfUpdatedProducts" type="{http://localhost:8084/EBUT_Wholesaler_Zweite_Instanz/UpdateCatalogWebService}listOfUpdatedProducts"/>
 *         &lt;element name="listOfUnavailableProducts" type="{http://localhost:8084/EBUT_Wholesaler_Zweite_Instanz/UpdateCatalogWebService}listOfUnavailableProducts"/>
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
    "updateDate",
    "listOfUpdatedProducts",
    "listOfUnavailableProducts"
})
@XmlRootElement(name = "updateResponse")
public class UpdateResponse {

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar updateDate;
    @XmlElement(required = true)
    protected ListOfUpdatedProducts listOfUpdatedProducts;
    @XmlElement(required = true)
    protected ListOfUnavailableProducts listOfUnavailableProducts;

    /**
     * Ruft den Wert der updateDate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateDate() {
        return updateDate;
    }

    /**
     * Legt den Wert der updateDate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateDate(XMLGregorianCalendar value) {
        this.updateDate = value;
    }

    /**
     * Ruft den Wert der listOfUpdatedProducts-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ListOfUpdatedProducts }
     *     
     */
    public ListOfUpdatedProducts getListOfUpdatedProducts() {
        return listOfUpdatedProducts;
    }

    /**
     * Legt den Wert der listOfUpdatedProducts-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfUpdatedProducts }
     *     
     */
    public void setListOfUpdatedProducts(ListOfUpdatedProducts value) {
        this.listOfUpdatedProducts = value;
    }

    /**
     * Ruft den Wert der listOfUnavailableProducts-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ListOfUnavailableProducts }
     *     
     */
    public ListOfUnavailableProducts getListOfUnavailableProducts() {
        return listOfUnavailableProducts;
    }

    /**
     * Legt den Wert der listOfUnavailableProducts-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfUnavailableProducts }
     *     
     */
    public void setListOfUnavailableProducts(ListOfUnavailableProducts value) {
        this.listOfUnavailableProducts = value;
    }

}
