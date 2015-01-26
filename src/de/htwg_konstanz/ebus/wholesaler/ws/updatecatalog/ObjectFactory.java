
package de.htwg_konstanz.ebus.wholesaler.ws.updatecatalog;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.htwg_konstanz.ebus.wholesaler.ws.updatecatalog package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FaultType_QNAME = new QName("http://localhost:8084/EBUT_Wholesaler/updatecatalog", "faultType");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.htwg_konstanz.ebus.wholesaler.ws.updatecatalog
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UpdateRequest }
     * 
     */
    public UpdateRequest createUpdateRequest() {
        return new UpdateRequest();
    }

    /**
     * Create an instance of {@link Authentication }
     * 
     */
    public Authentication createAuthentication() {
        return new Authentication();
    }

    /**
     * Create an instance of {@link ListOfProducts }
     * 
     */
    public ListOfProducts createListOfProducts() {
        return new ListOfProducts();
    }

    /**
     * Create an instance of {@link UpdateResponse }
     * 
     */
    public UpdateResponse createUpdateResponse() {
        return new UpdateResponse();
    }

    /**
     * Create an instance of {@link ListOfUpdatedProducts }
     * 
     */
    public ListOfUpdatedProducts createListOfUpdatedProducts() {
        return new ListOfUpdatedProducts();
    }

    /**
     * Create an instance of {@link ListOfUnavailableProducts }
     * 
     */
    public ListOfUnavailableProducts createListOfUnavailableProducts() {
        return new ListOfUnavailableProducts();
    }

    /**
     * Create an instance of {@link SupplierProduct }
     * 
     */
    public SupplierProduct createSupplierProduct() {
        return new SupplierProduct();
    }

    /**
     * Create an instance of {@link Price }
     * 
     */
    public Price createPrice() {
        return new Price();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://localhost:8084/EBUT_Wholesaler/updatecatalog", name = "faultType")
    public JAXBElement<String> createFaultType(String value) {
        return new JAXBElement<String>(_FaultType_QNAME, String.class, null, value);
    }

}
