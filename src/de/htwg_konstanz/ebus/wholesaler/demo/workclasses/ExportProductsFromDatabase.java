/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg_konstanz.ebus.wholesaler.demo.workclasses;

/**
 *
 * @author Felix
 */
public final class ExportProductsFromDatabase {

    /**
     * Private instanz Variable.
     */
    private static ExportProductsFromDatabase instance;

    /**
     * Private Konstruktor für Singleton Pattern.
     */
    private ExportProductsFromDatabase() {

    }

    /**
     * Singleton get Instance.
     *
     * @return XmlParser
     */
    public static ExportProductsFromDatabase getInstance() {
        if (instance == null) {
            instance = new ExportProductsFromDatabase();
        }
        return instance;
    }

    public void export(final String filter, final String bmecat, final String xhtml) {

    }
}
