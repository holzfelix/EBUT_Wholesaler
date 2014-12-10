/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg_konstanz.ebus.wholesaler.demo.workclasses;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Felix
 */
public final class Upload {

    /**
     * Konstant for the Byte Size.
     */
    private static final int BYTE = 8192;

    /**
     * Private Konstruktor für Singleton Pattern.
     */
    public Upload() {

    }

    /**
     * Receives the XML File and generates an InputStream.
     *
     * @param request HttpServletRequest
     * @return InputStream Stream of the uploaded file
     * @throws FileUploadException Exception Handling for File Upload
     * @throws IOException Exception Handling for IO Exceptions
     */
    public InputStream upload(final HttpServletRequest request) throws FileUploadException, IOException {
        // Check Variable to check if it is a File Uploade
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            //Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            //Console Out Starting the Upload progress.
            System.out.println("\n - - - UPLOAD");

            // List of all uploaded Files
            List files = upload.parseRequest(request);

            // Returns the uploaded File
            Iterator iter = files.iterator();

            FileItem element = (FileItem) iter.next();
            String fileName = element.getName();
            String extension = FilenameUtils.getExtension(element.getName());

            // check if file extension is xml, when not then it will be aborted
            if (!extension.equals("xml")) {
                return null;
            }

            System.out.println("Extension:" + extension);

            System.out.println("\nFilename: " + fileName);

            // Escaping Special Chars
            fileName = fileName.replace('/', '\\');
            fileName = fileName.substring(fileName.lastIndexOf('\\') + 1);

            // Converts the File into an Input Strem
            InputStream is;
            is = element.getInputStream();

            return is;
        }
        return null;
    }

}
