/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg_konstanz.ebus.wholesaler.demo;

import de.htwg_konstanz.ebus.framework.wholesaler.api.security.Security;
import static de.htwg_konstanz.ebus.wholesaler.demo.ExportXML.PARAM_LOGIN_BEAN;
import de.htwg_konstanz.ebus.wholesaler.demo.util.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.xml.sax.SAXException;

/**
 *
 * @author Felix
 */
public class ImportXML implements IAction {

    /**
     * Gets the Instanz of the Upload class.
     */
    private final Upload upload = Upload.getInstance();

    /**
     * Gets the instace of the Xml Parser.
     */
    private final XmlParser xmlParser = XmlParser.getInstance();

    /**
     * Gets the instance of the SaveProductsToDatabase Class.
     */
    private final SaveProductsToDatabase saveProduct = SaveProductsToDatabase.getInstance();

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

                /*--------------------------------------
                 -- Start Import
                 ----------------------------------------*/
                // Input Stream which has to be parsed
                InputStream is;
                // Check if the form was sended
                if (ServletFileUpload.isMultipartContent(request)) {
                    try {
                        is = upload.upload(request);
                        // Server-Side check if a File was chosen or if it was an emty File
                        if (is == null) {
                            return "importxml.jsp?nofile=File was empty!!!!!!! Or wrong file-type. only xml is allowed";
                        }

                        // Returns the valid XML document
                        org.w3c.dom.Document document = xmlParser.validateTheXml(is);
                        if (document == null) {
                            return "importxml.jsp?nofile=Sorry your xml-file was not valid.";
                        }
                        // Reads the XML-File and saves the Products to the Database
                        saveProduct.readXML(document);

                        // XML File Validieeren
                    } catch (FileUploadException | IOException | SAXException | ParserConfigurationException ex) {
                        Logger.getLogger(ImportXML.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                // Rendering the import page
                return "importxml.jsp";
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
        return actionName.equalsIgnoreCase(Constants.ACTION_SHOW_IMPORTJSP);
    }

}
