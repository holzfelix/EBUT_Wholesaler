/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg_konstanz.ebus.wholesaler.demo;

import java.io.IOException;
import java.io.InputStream;
import org.xml.sax.SAXException;

/**
 *
 * @author Felix
 */
public final class XmlParser {

    /**
     * Private instanz Variable.
     */
    private static XmlParser instance;

    /**
     * Private Konstruktor für Singleton Pattern.
     */
    private XmlParser() {

    }

    /**
     * Singleton get Instance.
     *
     * @return XmlParser
     */
    public static XmlParser getInstance() {
        if (instance == null) {
            instance = new XmlParser();
        }
        return instance;
    }

    /**
     * Validates the XML.
     *
     * @param is InputStream
     * @return boolean Validate Status of the XML.
     */
    public boolean validateTheXml(final InputStream is) throws IOException, SAXException {
        return checkWellformness(is);
    }

    /**
     * Checks the Wellformness of the selected XML File.
     *
     * @param is InputStream
     * @return boolean
     */
    private boolean checkWellformness(final InputStream is) throws IOException, SAXException {

        return false;
    }

}
