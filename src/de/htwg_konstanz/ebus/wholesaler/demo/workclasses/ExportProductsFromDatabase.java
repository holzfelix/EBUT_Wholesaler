/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg_konstanz.ebus.wholesaler.demo.workclasses;

import de.htwg_konstanz.ebus.framework.wholesaler.api.bo.BOProduct;
import de.htwg_konstanz.ebus.framework.wholesaler.api.boa.ProductBOA;
import java.util.Collection;

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

    private Collection<BOProduct> products;

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

        // Checks if the whole catalogue should be exported or only selective export products
        if (filter != null) {
            products = ProductBOA.getInstance().findByCriteriaLike("Shortdescription", "%" + filter + "%");
            System.out.println(products.size() + " products found");
        } else {
            products = ProductBOA.getInstance().findAll();
            System.out.println(products.size() + " all products selected");
        }

    }
}
