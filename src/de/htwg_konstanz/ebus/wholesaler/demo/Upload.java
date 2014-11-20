/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg_konstanz.ebus.wholesaler.demo;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Felix
 */
public final class Upload {

    /**
     * Private instanz Variable.
     */
    private static Upload instance;

    /**
     * Private Konstruktor für Singleton Pattern.
     */
    private Upload() {

    }

    /**
     * Singleton get Instance.
     *
     * @return Upload
     */
    public static Upload getInstance() {
        if (instance == null) {
            instance = new Upload();
        }
        return instance;
    }

    /**
     * Epfängt das XML File.
     *
     * @param request HttpServletRequest
     */
    public final void upload(HttpServletRequest request) {
        // Check Variable to check if it is a File Uploade
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {

            System.out.println("\n - - - UPLOAD");

        }
    }

}
