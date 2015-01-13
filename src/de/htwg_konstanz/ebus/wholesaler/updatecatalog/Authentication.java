
package de.htwg_konstanz.ebus.wholesaler.updatecatalog;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für authentication complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="authentication">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wholesalerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="wsUsername" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="wsPassword" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "authentication", propOrder = {
    "wholesalerName",
    "wsUsername",
    "wsPassword"
})
public class Authentication {

    @XmlElement(required = true)
    protected String wholesalerName;
    @XmlElement(required = true)
    protected String wsUsername;
    @XmlElement(required = true)
    protected String wsPassword;

    /**
     * Ruft den Wert der wholesalerName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWholesalerName() {
        return wholesalerName;
    }

    /**
     * Legt den Wert der wholesalerName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWholesalerName(String value) {
        this.wholesalerName = value;
    }

    /**
     * Ruft den Wert der wsUsername-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWsUsername() {
        return wsUsername;
    }

    /**
     * Legt den Wert der wsUsername-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWsUsername(String value) {
        this.wsUsername = value;
    }

    /**
     * Ruft den Wert der wsPassword-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWsPassword() {
        return wsPassword;
    }

    /**
     * Legt den Wert der wsPassword-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWsPassword(String value) {
        this.wsPassword = value;
    }

}
