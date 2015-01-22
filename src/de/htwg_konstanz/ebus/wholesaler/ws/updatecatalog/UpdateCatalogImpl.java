/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg_konstanz.ebus.wholesaler.ws.updatecatalog;

import de.htwg_konstanz.ebus.framework.wholesaler.api.bo.BOProduct;
import de.htwg_konstanz.ebus.framework.wholesaler.api.boa.ProductBOA;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;

/**
 *
 * @author Felix
 */
@ServiceMode(value = javax.xml.ws.Service.Mode.PAYLOAD)
@WebServiceProvider(serviceName = "UpdateCatalogWebService", portName = "updateCatalogPort", targetNamespace = "http://192.168.178.39:8084/EBUT_Wholesaler/UpdateCatalogWebService", wsdlLocation = "WEB-INF/wsdl/UpdateCatalogImpl/updateCatalogue.wsdl")
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
    public final UpdateResponse updateCatalog(UpdateRequest updateRequest) throws AuthenticationFault {

        /**
         * Generates a response object.
         */
        UpdateResponse response = factory.createUpdateResponse();
        /**
         * List of the Products which were send to the Service.
         */
        ListOfProducts productList = updateRequest.getListOfProducts();

        System.out.println("Zu verarbeitendeprodukte: " + productList.getSupplierProduct().size());

        ListOfUnavailableProducts unavailableProducts = new ListOfUnavailableProducts();
        ListOfUpdatedProducts updatedProducts = new ListOfUpdatedProducts();

        String responseString;
        int changedProduct = 0;
        int notChangedProuct = 0;

        try {
            System.out.println("Host Name/Adresse: " + InetAddress.getLocalHost());
        } catch (UnknownHostException ex) {
            Logger.getLogger(UpdateCatalogImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        products = ProductBOA.getInstance().findAll();

        /**
         * Iteration over all Products
         */
        for (SupplierProduct productSupplier : productList.getSupplierProduct()) {
            BOProduct product = productBOA.findByOrderNumberSupplier(productSupplier.getSupplierAID());

            // Checks if product exists
            if (product != null) {
                boolean hasLongDescriptionChanged = productSupplier.getLongDescription().equals(product.getLongDescription());
                boolean hasShortDescriptionChanged = productSupplier.getShortDescription().equals(product.getShortDescription());

                // Checks if the Long or Short description has changed
                if ((!hasLongDescriptionChanged || !hasShortDescriptionChanged)) {
                    updatedProducts.getSupplierProduct().add(genSupplierProduct(product));
                } else {
                    //responseString ist gleich
                    responseString = "LongDescription or ShortDescription not changed :" + changedProduct++;
                }
            } else {
                // If product isn't available anymore.
                responseString = "set supplier product to unvisible product: " + notChangedProuct++;
                unavailableProducts.getSupplierProduct().add(genSupplierProduct(product));
            }
        }

        // Response to WebService caller
        response.setListOfUnavailableProducts(unavailableProducts);
        response.setListOfUpdatedProducts(updatedProducts);
        return response;
    }

    /**
     * Generates a product to be set.
     *
     * @param product BOProduct
     * @return SupplierProduct
     */
    private SupplierProduct genSupplierProduct(final BOProduct product) {
        SupplierProduct sP = new SupplierProduct();
        sP.setLongDescription(product.getLongDescription());
        sP.setShortDescription(product.getShortDescription());
        sP.setSupplierAID(product.getSupplier().getSupplierNumber());
        return sP;
    }

}
