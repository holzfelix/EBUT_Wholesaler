/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg_konstanz.ebus.wholesaler.ws.updatecatalog;

import de.htwg_konstanz.ebus.framework.wholesaler.api.bo.BOProduct;
import de.htwg_konstanz.ebus.framework.wholesaler.api.boa.ProductBOA;
import java.util.Collection;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;

/**
 *
 * @author Felix
 */
@ServiceMode(value = javax.xml.ws.Service.Mode.PAYLOAD)
@WebServiceProvider(serviceName = "UpdateCatalogWebService", portName = "updateCatalogPort", targetNamespace = "http://localhost:8084/EBUT_Wholesaler/updatecatalog", wsdlLocation = "WEB-INF/wsdl/UpdateCatalogImpl/updateCatalogue.wsdl")
public class UpdateCatalogImpl implements javax.xml.ws.Provider<javax.xml.transform.Source>, UpdateCatalog {

    /**
     * Collection of the Products.
     */
    private Collection<BOProduct> products;

    /**
     * Object Factory.
     */
    private final ObjectFactory factory = new ObjectFactory();

    public javax.xml.transform.Source invoke(javax.xml.transform.Source source) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public UpdateResponse updateCatalog(UpdateRequest updateRequest) throws AuthenticationFault {

        /**
         * Generates a response object.
         */
        UpdateResponse response = factory.createUpdateResponse();
        /**
         * List of the Products which were send to the Service.
         */
        ListOfProducts productList = updateRequest.getListOfProducts();

        ListOfUnavailableProducts unavailableProducts = factory.createListOfUnavailableProducts();
        ListOfUpdatedProducts updatedProducts = factory.createListOfUpdatedProducts();

        products = ProductBOA.getInstance().findAll();

        for (SupplierProduct productSupplier : productList.getSupplierProduct()) {

        }

        return response;
    }

}
