/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg_konstanz.ebus.wholesaler.demo;

/**
 *
 * @author Felix
 */
public class SaveProductsToDatabase {

    /**
     * Private instanz Variable.
     */
    private static SaveProductsToDatabase instance;

    /**
     * Private Konstruktor für Singleton Pattern.
     */
    private SaveProductsToDatabase() {

    }

    /**
     * Singleton get Instance.
     *
     * @return XmlParser
     */
    public static SaveProductsToDatabase getInstance() {
        if (instance == null) {
            instance = new SaveProductsToDatabase();
        }
        return instance;
    }

    public void readXML(final org.w3c.dom.Document xmlFile) {

    }

}
