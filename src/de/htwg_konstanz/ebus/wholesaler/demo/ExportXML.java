/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg_konstanz.ebus.wholesaler.demo;

import de.htwg_konstanz.ebus.framework.wholesaler.api.bo.IBOUser;
import de.htwg_konstanz.ebus.framework.wholesaler.api.security.Security;
import de.htwg_konstanz.ebus.wholesaler.demo.util.Constants;
import de.htwg_konstanz.ebus.wholesaler.demo.workclasses.ExportProductsFromDatabase;
import java.util.ArrayList;
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

    /**
     * Gets the Instanz of the Upload class.
     */
    private final ExportProductsFromDatabase export = ExportProductsFromDatabase.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ArrayList<String> errorList) {

        String returnpath = "exportxml.jsp";

        // get the login bean from the session
        LoginBean loginBean = (LoginBean) request.getSession(true).getAttribute(PARAM_LOGIN_BEAN);

        // ensure that the user is logged in
        if (loginBean != null && loginBean.isLoggedIn()) {
            // ensure that the user is allowed to execute this action (authorization)
            // at this time the authorization is not fully implemented.
            // -> use the "Security.RESOURCE_ALL" constant which includes all resources.

            IBOUser user = loginBean.getUser();

            if (Security.getInstance().isUserAllowed(user, Security.ACTION_READ, Security.ACTION_READ)) {

                String filter = (String) request.getParameter("substring");
                String bmecat = (String) request.getParameter("BMEcat");
                String xhtml = (String) request.getParameter("XHTML");

                // Check if an output format is chosen
                if (!(bmecat == null && xhtml == null)) {
                    System.out.println("Start exporting");
                    System.out.println(filter);
                    System.out.println(bmecat);

                    export.export(filter, bmecat, xhtml);
                    returnpath = "exportxml.jsp?infomessage=Well done.";
                } else {
                    return "exportxml.jsp?errormessage=Please select at least one output format!";
                }

                // redirect to the product page
                return returnpath;
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
