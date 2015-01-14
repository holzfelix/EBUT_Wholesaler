/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg_konstanz.ebus.wholesaler.demo;

import de.htwg_konstanz.ebus.framework.wholesaler.api.security.Security;
import static de.htwg_konstanz.ebus.wholesaler.demo.ExportXML.PARAM_LOGIN_BEAN;
import de.htwg_konstanz.ebus.wholesaler.demo.util.Constants;
import de.htwg_konstanz.ebus.wholesaler.ws.updatecatalog.AuthenticationFault;
import de.htwg_konstanz.ebus.wholesaler.ws.updatecatalog.UpdateCatalogImpl;
import de.htwg_konstanz.ebus.wholesaler.ws.updatecatalog.UpdateRequest;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felix
 */
public class UpdateCatalog implements IAction {

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

                UpdateRequest updateRequest = new UpdateRequest();
                try {
                    UpdateCatalogImpl test = new UpdateCatalogImpl();
                    test.updateCatalog(updateRequest);
                } catch (AuthenticationFault ex) {
                    Logger.getLogger(UpdateCatalog.class.getName()).log(Level.SEVERE, null, ex);
                }

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
        return null;
    }

    @Override
    public final boolean accepts(final String actionName) {
        return actionName.equalsIgnoreCase(Constants.ACTION_UPDATE_CATALOG);
    }

}
