/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg_konstanz.ebus.wholesaler.demo.workclasses;

import org.w3c.dom.Document;

/**
 *
 * @author Felix
 */
public class ParseReportDTO {

    /**
     * Saves the DOM document.
     */
    private org.w3c.dom.Document doc;
    /**
     * Report message.
     */
    private String message;

    /**
     * Construktor.
     *
     * @param docIN org.w3c.dom.Document
     * @param messageIN String
     */
    public ParseReportDTO(final Document docIN, final String messageIN) {
        this.doc = docIN;
        this.message = messageIN;
    }

    /**
     * Returns the document.
     *
     * @return org.w3c.dom.Document
     */
    public final Document getDoc() {
        return doc;
    }

    /**
     * Sets the document.
     *
     * @param docIN org.w3c.dom.Document
     */
    public final void setDoc(final Document docIN) {
        this.doc = docIN;
    }

    /**
     * Returns the messae Error / Info.
     *
     * @return String
     */
    public final String getMessage() {
        return message;
    }

    /**
     * To set the Error / Info message.
     *
     * @param messageIN String
     */
    public final void setMessage(final String messageIN) {
        this.message = messageIN;
    }

}
