/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg_konstanz.ebus.wholesaler.demo;

import de.htwg_konstanz.ebus.wholesaler.demo.util.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileUploadException;

/**
 *
 * @author Felix
 */
public class ImportXML implements IAction {

    /**
     * Holt die Instanz der Upload Klasse.
     */
    private final Upload upload = Upload.getInstance();

    @Override
    public final String execute(final HttpServletRequest request, final HttpServletResponse response, final ArrayList<String> errorList) {

        try {
            InputStream is = upload.upload(request);
            // Server-Side check if a File was chosen.
            if (is == null) {
                return "importxml.jsp?nofile=File was empty!!!!!!!";
            }
        } catch (FileUploadException | IOException ex) {
            Logger.getLogger(ImportXML.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Renders the import page
        return "importxml.jsp";
    }

    @Override
    public final boolean accepts(final String actionName) {
        return actionName.equalsIgnoreCase(Constants.ACTION_SHOW_IMPORTJSP);
    }

}
