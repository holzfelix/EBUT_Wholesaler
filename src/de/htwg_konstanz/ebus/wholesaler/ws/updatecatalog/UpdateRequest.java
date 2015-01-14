
package de.htwg_konstanz.ebus.wholesaler.ws.updatecatalog;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse f�r anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="authentication" type="{http://localhost:8084/EBUT_Wholesaler/updatecatalog}authentication"/>
 *         &lt;element name="listOfProducts" type="{http://localhost:8084/EBUT_Wholesaler/updatecatalog}listOfProducts"/>
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
    "authentication",
    "listOfProducts"
})
@XmlRootElement(name = "updateRequest")
public class UpdateRequest {

    @XmlElement(required = true)
    protected Authentication authentication;
    @XmlElement(required = true)
    protected ListOfProducts listOfProducts;

    /**
     * Ruft den Wert der authentication-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Authentication }
     *     
     */
    public Authentication getAuthentication() {
        return authentication;
    }

    /**
     * Legt den Wert der authentication-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Authentication }
     *     
     */
    public void setAuthentication(Authentication value) {
        this.authentication = value;
    }

    /**
     * Ruft den Wert der listOfProducts-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ListOfProducts }
     *     
     */
    public ListOfProducts getListOfProducts() {
        return listOfProducts;
    }

    /**
     * Legt den Wert der listOfProducts-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfProducts }
     *     
     */
    public void setListOfProducts(ListOfProducts value) {
        this.listOfProducts = value;
    }

}
