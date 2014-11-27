/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg_konstanz.ebus.wholesaler.demo;

import de.htwg_konstanz.ebus.framework.wholesaler.api.bo.IBOUser;
import de.htwg_konstanz.ebus.framework.wholesaler.api.boa.ProductBOA;
import de.htwg_konstanz.ebus.framework.wholesaler.api.security.Security;
import de.htwg_konstanz.ebus.wholesaler.demo.util.Constants;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felix
 */
public class ExportXML implements IAction {

    public static final String ACTION_SHOW_PRODUCT_LIST = "showProductList";
    public static final String PARAM_LOGIN_BEAN = "loginBean";
    private static final String PARAM_PRODUCT_LIST = "productList";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ArrayList<String> errorList) {
        // get the login bean from the session
        LoginBean loginBean = (LoginBean) request.getSession(true).getAttribute(PARAM_LOGIN_BEAN);

        // ensure that the user is logged in
        if (loginBean != null && loginBean.isLoggedIn()) {
            // ensure that the user is allowed to execute this action (authorization)
            // at this time the authorization is not fully implemented.
            // -> use the "Security.RESOURCE_ALL" constant which includes all resources.

            IBOUser user = loginBean.getUser();

            if (Security.getInstance().isUserAllowed(user, Security.ACTION_READ, Security.ACTION_READ)) {
                // find all available products and put it to the session
                List<?> productList = ProductBOA.getInstance().findAll();
                request.getSession(true).setAttribute(PARAM_PRODUCT_LIST, productList);

                // redirect to the product page
                return "exportxml.jsp";
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
    public boolean accepts(String actionName) {
        return actionName.equalsIgnoreCase(Constants.ACTION_SHOW_EXPORTJSP);
    }

}
