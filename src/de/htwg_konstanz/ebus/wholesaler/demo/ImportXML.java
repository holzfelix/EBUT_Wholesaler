/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg_konstanz.ebus.wholesaler.demo;

import de.htwg_konstanz.ebus.wholesaler.demo.util.Constants;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Felix
 */
public class ImportXML implements IAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ArrayList<String> errorList) {
        // Check Variable to check if it is a File Uploade
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            try {
                ServletFileUpload upload = new ServletFileUpload();
                FileItemIterator iter = upload.getItemIterator(request);

                while (iter.hasNext()) {
                    FileItemStream item = iter.next();
                    String name = item.getFieldName();
                    InputStream stream = item.openStream();

                    System.out.println("File field " + name + " with file name "
                            + item.getName() + " detected.");
                    // Process the input stream
                    File f = new File("/" + item.getName());

                    System.out.println(f.getAbsolutePath());

//                    FileOutputStream fout = new FileOutputStream(f);
//                    BufferedOutputStream bout = new BufferedOutputStream(fout);
//                    BufferedInputStream bin = new BufferedInputStream(stream);
                }

                // Renders the import page
                return "importxml.jsp";
            } catch (FileUploadException | IOException ex) {
                Logger.getLogger(ImportXML.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return "importxml.jsp";
    }

    @Override
    public boolean accepts(String actionName) {
        return actionName.equalsIgnoreCase(Constants.ACTION_SHOW_IMPORTJSP);
    }

}
