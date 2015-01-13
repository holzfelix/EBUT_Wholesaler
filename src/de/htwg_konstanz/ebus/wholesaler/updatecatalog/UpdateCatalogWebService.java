
package de.htwg_konstanz.ebus.wholesaler.updatecatalog;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "UpdateCatalogWebService", targetNamespace = "http://localhost:8084/EBUT_Wholesaler/updatecatalog", wsdlLocation = "file:/C:/Users/Felix/Dropbox/Studium/WIN/Semester_6/EBUT/\u00dcbung/Assignment4/updateCatalogue.wsdl")
public class UpdateCatalogWebService
    extends Service
{

    private final static URL UPDATECATALOGWEBSERVICE_WSDL_LOCATION;
    private final static WebServiceException UPDATECATALOGWEBSERVICE_EXCEPTION;
    private final static QName UPDATECATALOGWEBSERVICE_QNAME = new QName("http://localhost:8084/EBUT_Wholesaler/updatecatalog", "UpdateCatalogWebService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Users/Felix/Dropbox/Studium/WIN/Semester_6/EBUT/\u00dcbung/Assignment4/updateCatalogue.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        UPDATECATALOGWEBSERVICE_WSDL_LOCATION = url;
        UPDATECATALOGWEBSERVICE_EXCEPTION = e;
    }

    public UpdateCatalogWebService() {
        super(__getWsdlLocation(), UPDATECATALOGWEBSERVICE_QNAME);
    }

    public UpdateCatalogWebService(WebServiceFeature... features) {
        super(__getWsdlLocation(), UPDATECATALOGWEBSERVICE_QNAME, features);
    }

    public UpdateCatalogWebService(URL wsdlLocation) {
        super(wsdlLocation, UPDATECATALOGWEBSERVICE_QNAME);
    }

    public UpdateCatalogWebService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, UPDATECATALOGWEBSERVICE_QNAME, features);
    }

    public UpdateCatalogWebService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public UpdateCatalogWebService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns UpdateCatalog
     */
    @WebEndpoint(name = "updateCatalogPort")
    public UpdateCatalog getUpdateCatalogPort() {
        return super.getPort(new QName("http://localhost:8084/EBUT_Wholesaler/updatecatalog", "updateCatalogPort"), UpdateCatalog.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns UpdateCatalog
     */
    @WebEndpoint(name = "updateCatalogPort")
    public UpdateCatalog getUpdateCatalogPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://localhost:8084/EBUT_Wholesaler/updatecatalog", "updateCatalogPort"), UpdateCatalog.class, features);
    }

    private static URL __getWsdlLocation() {
        if (UPDATECATALOGWEBSERVICE_EXCEPTION!= null) {
            throw UPDATECATALOGWEBSERVICE_EXCEPTION;
        }
        return UPDATECATALOGWEBSERVICE_WSDL_LOCATION;
    }

}
