/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg_konstanz.ebus.wholesaler.demo;

import de.htwg_konstanz.ebus.framework.wholesaler.api.bo.BOProduct;
import de.htwg_konstanz.ebus.framework.wholesaler.api.boa.ProductBOA;
import de.htwg_konstanz.ebus.framework.wholesaler.api.security.Security;
import static de.htwg_konstanz.ebus.wholesaler.demo.ExportXML.PARAM_LOGIN_BEAN;
import de.htwg_konstanz.ebus.wholesaler.demo.util.Constants;
import de.htwg_konstanz.ebus.wholesaler.ws.updatecatalog.AuthenticationFault;
import de.htwg_konstanz.ebus.wholesaler.ws.updatecatalog.ListOfProducts;
import de.htwg_konstanz.ebus.wholesaler.ws.updatecatalog.ObjectFactory;
import de.htwg_konstanz.ebus.wholesaler.ws.updatecatalog.Price;
import de.htwg_konstanz.ebus.wholesaler.ws.updatecatalog.SupplierProduct;
import de.htwg_konstanz.ebus.wholesaler.ws.updatecatalog.UpdateCatalogWebService;
import de.htwg_konstanz.ebus.wholesaler.ws.updatecatalog.UpdateRequest;
import de.htwg_konstanz.ebus.wholesaler.ws.updatecatalog.UpdateResponse;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felix
 */
public class UpdateCatalogWS implements IAction {

    /**
     * Collection of the Products.
     */
    private Collection<BOProduct> products;
    /**
     * Response from WS.
     */
    private UpdateResponse res;

    private final ObjectFactory factory = new ObjectFactory();

    /**
     * Instance of the Product Business Object.
     */
    private final ProductBOA productBOA = ProductBOA.getInstance();

    @Override
    public final String execute(final HttpServletRequest request, final HttpServletResponse response, final ArrayList<String> errorList) {
        // get the login bean from the session
        LoginBean loginBean = (LoginBean) request.getSession(true).getAttribute(PARAM_LOGIN_BEAN);

        // ensure that the user is logged in
        if (loginBean != null && loginBean.isLoggedIn()) {
            // ensure that the user is allowed to execute this action (authorization)
            // at this time the authorization is not fully implemented.
            // -> use the "Security.RESOURCE_ALL" constant which includes all resources.
            if (Security.getInstance().isUserAllowed(loginBean.getUser(), Security.RESOURCE_ALL, Security.ACTION_READ)) {

                String supplierID = request.getParameter("supplier");

                System.out.println("SupplierID: " + supplierID);

                ListOfProducts productList = new ListOfProducts();

                /// Get all Products from Databse
                products = productBOA.findAll();

                for (BOProduct product : products) {
                    if (product.getSupplier().getSupplierNumber().equals(supplierID)) {
                        SupplierProduct p = new SupplierProduct();
                        p.setLongDescription(product.getLongDescription());
                        p.setShortDescription(product.getShortDescription());
                        p.setSupplierAID(product.getOrderNumberSupplier());

                        List prices = p.getPrice();

                        Price price = new Price();
                        price.setAmount(BigDecimal.ZERO);
                        price.setCountryISOCode(PARAM_LOGIN_BEAN);
                        price.setCurrency(PARAM_LOGIN_BEAN);
                        price.setLowerBound(BigInteger.ZERO);
                        price.setPricetype(PARAM_LOGIN_BEAN);
                        price.setTax(BigDecimal.ZERO);

                        prices.add(price);

                        productList.getSupplierProduct().add(p);
                    }

                }

                System.out.println("Anzahl Produkte in productList " + productList.getSupplierProduct().size());

                //res = factory.createUpdateResponse();
                res = new UpdateResponse();

                UpdateCatalogWebService service = null;

                try {
                    service = new UpdateCatalogWebService(new URL("http://192.168.178.39:8084/EBUT_Wholesaler/wsdl/updateCatalogue.wsdl"));
                } catch (MalformedURLException ex) {
                    Logger.getLogger(UpdateCatalogWS.class.getName()).log(Level.SEVERE, null, ex);
                }

                UpdateCatalog u = (UpdateCatalog) service.getUpdateCatalogPort();

                UpdateRequest updateRequest = new UpdateRequest();
                updateRequest.setListOfProducts(productList);

                try {
                    service.getUpdateCatalogPort().updateCatalog(updateRequest);
                    //res = new UpdateCatalogWebService().getUpdateCatalogPort().updateCatalog(updateRequest);
                    //res = new UpdateCatalogImpl().updateCatalog(updateRequest);

                } catch (AuthenticationFault ex) {
                    Logger.getLogger(UpdateCatalogWS.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Unavailable: " + res.getListOfUnavailableProducts().getSupplierProduct().size());
                System.out.println("Updated: " + res.getListOfUpdatedProducts().getSupplierProduct().size());

                return "updatecatalogresult.jsp?infomessage=" + res.getListOfUpdatedProducts().getSupplierProduct().size();

            } else {
                // authorization failed -> show error message
                errorList.add("You are not allowed to perform this action!");
                // redirect to the welcome page
                return "welcome.jsp";
            }
        } else {
            // redirect to the login page
            return "login.jsp";
        }
    }

    @Override
    public final boolean accepts(final String actionName) {
        return actionName.equalsIgnoreCase(Constants.ACTION_UPDATE_CATALOGWS);
    }

}
