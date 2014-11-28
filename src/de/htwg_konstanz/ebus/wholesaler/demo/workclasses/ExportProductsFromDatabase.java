/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg_konstanz.ebus.wholesaler.demo.workclasses;

import de.htwg_konstanz.ebus.framework.wholesaler.api.bo.BOProduct;
import de.htwg_konstanz.ebus.framework.wholesaler.api.boa.ProductBOA;
import java.util.Collection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
     * Collection of the Products.
     */
    private Collection<BOProduct> products;
    private Document doc;

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

    /**
     * Selects the Products to export.
     *
     * @param filter String
     * @param bmecat String
     * @param xhtml String
     */
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

    public void exportout(final String bmecat, final String xhtml) throws ParserConfigurationException {
        if (bmecat != null) {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();
        }

        if (xhtml != null) {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();

        }
    }

    private void createDocument() {

        // creating the root element and adding the Prolog and namespace
        Element root = doc.createElement("BMECAT");
        root.setAttribute("version", "1.2");
        root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");

        Element header = doc.createElement("HEADER");
        Element catalog = doc.createElement("CATALOG");
        Element supplier = doc.createElement("SUPPLIER");
        Element language = doc.createElement("LANGUAGE");
        Element catalogId = doc.createElement("CATALOG_ID");
        Element catalogVersion = doc.createElement("CATALOG_VERSION");
        Element catalogName = doc.createElement("CATALOG_NAME");
        Element supplierName = doc.createElement("SUPPLIER_NAME");

        header.appendChild(catalog);
        header.appendChild(supplier);

        catalog.appendChild(language);
        catalog.appendChild(catalogId);
        catalog.appendChild(catalogVersion);
        catalog.appendChild(catalogName);

        supplier.appendChild(supplierName);

        language.setTextContent("deu");
        catalogId.setTextContent("#HTWG-EBUS-12");
        catalogVersion.setTextContent("1.0");
        catalogName.setTextContent("Beispielproduktkatalog für E-Business Laborpraktika");

        supplierName.setTextContent("HTWG");

        Element tNewCatalog = createTNewCatalog();

        root.appendChild(header);
        root.appendChild(tNewCatalog);
        doc.appendChild(root);
    }

    private Element createTNewCatalog() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
