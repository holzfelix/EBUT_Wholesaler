/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg_konstanz.ebus.wholesaler.ws.updatecatalog;

import de.htwg_konstanz.ebus.framework.wholesaler.api.bo.BOProduct;
import de.htwg_konstanz.ebus.framework.wholesaler.api.boa.ProductBOA;
import java.util.Collection;
import java.util.List;
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
     * Instance of the Product Business Object.
     */
    private final ProductBOA productBOA = ProductBOA.getInstance();

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

        List unavailableProducts = new ListOfUnavailableProducts().getSupplierProduct();
        List updatedProducts = new ListOfUpdatedProducts().getSupplierProduct();

        String responseString;
        int changedProduct = 0;
        int notChangedProuct = 0;

        products = ProductBOA.getInstance().findAll();

        /**
         * Iteration over all Products
         */
        for (SupplierProduct productSupplier : productList.getSupplierProduct()) {
            BOProduct product = productBOA.findByOrderNumberSupplier(productSupplier.getSupplierAID());

            if (product != null) {
                boolean hasLongDescriptionChanged = false;
                hasLongDescriptionChanged = productSupplier.getLongDescription().equals(product.getLongDescription());
                boolean hasShortDescriptionChanged = false;
                hasShortDescriptionChanged = productSupplier.getShortDescription().equals(product.getShortDescription());
                if ((hasLongDescriptionChanged || hasShortDescriptionChanged)) {
                    updatedProducts.add(productSupplier);
                } else {
                    //responseString ist gleich
                    responseString = "LongDescription or ShortDescription not changed :" + changedProduct++;
                }

            } else {
                responseString = "set supplier product to unvisible product: " + notChangedProuct++;
                unavailableProducts.add(productSupplier);
            }

        }

        response.setListOfUnavailableProducts((ListOfUnavailableProducts) unavailableProducts);
        response.setListOfUpdatedProducts((ListOfUpdatedProducts) updatedProducts);

        return response;
    }

}
