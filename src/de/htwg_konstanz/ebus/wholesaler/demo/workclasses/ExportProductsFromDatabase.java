/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg_konstanz.ebus.wholesaler.demo.workclasses;

import de.htwg_konstanz.ebus.framework.wholesaler.api.bo.BOProduct;
import de.htwg_konstanz.ebus.framework.wholesaler.api.bo.BOSalesPrice;
import de.htwg_konstanz.ebus.framework.wholesaler.api.boa.PriceBOA;
import de.htwg_konstanz.ebus.framework.wholesaler.api.boa.ProductBOA;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Felix
 */
public class ExportProductsFromDatabase {

    /**
     * Private Konstruktor für Singleton Pattern.
     *
     */
    public ExportProductsFromDatabase() {
    }

    /**
     * Collection of the Products.
     */
    private Collection<BOProduct> products;
    /**
     * Generated XMLdocument.
     */
    private Document doc;

    /**
     * Selects the Products to export.
     *
     * @param filter String
     * @param bmecat String
     * @param xhtml String
     * @param response HttpServletResponse
     */
    public final void export(final String filter, final String bmecat, final String xhtml, final HttpServletResponse response) throws ParserConfigurationException, IOException, TransformerException {

        // Checks if the whole catalogue should be exported or only selective export products
        if (filter != null) {
            products = ProductBOA.getInstance().findByCriteriaLike("Shortdescription", "%" + filter + "%");
            System.out.println(products.size() + " products found");
        } else {
            products = ProductBOA.getInstance().findAll();
            System.out.println(products.size() + " all products selected");
        }

        exportout(bmecat, xhtml, response);

    }

    /**
     * Generating Exportoutput.
     *
     * @param bmecat String.
     * @param xhtml String.
     * @param response HttpServletResponse response
     * @throws ParserConfigurationException Exception Handling
     * @throws java.io.IOException Exception Handling
     * @throws javax.xml.transform.TransformerException Exception Handling
     */
    public final void exportout(final String bmecat, final String xhtml, final HttpServletResponse response) throws ParserConfigurationException, IOException, TransformerException {
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

        createDocument(response);
    }

    /**
     * Creates the Document.
     *
     * @param response HttpServletResponse
     * @throws IOException Exception Handling
     * @throws TransformerException Exception Handling
     */
    private void createDocument(final HttpServletResponse response) throws IOException, TransformerException {

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
        System.out.println("");

        // This should send the file to browser
        OutputStream out = response.getOutputStream();
        // Generating Result .. output steam
        Result result = new StreamResult(out);
        Source source = new DOMSource(doc);

        // Write the DOM document to the file in this case to the outputstream
        Transformer xformer = TransformerFactory.newInstance().newTransformer();
        xformer.transform(source, result);

        out.close();
        out.flush();

    }

    /**
     * Generets the Katalog (all above the T_New_Catalog tag)
     *
     * @return Element
     */
    private Element createTNewCatalog() {
        Element tNewCatalog = doc.createElement("T_NEW_CATALOG");
        for (BOProduct product : products) {
            Element article = doc.createElement("ARTICLE");

            // Gets the Supplier aid
            if (product.getOrderNumberCustomer() != null) {
                Element supplierAid = doc.createElement("SUPPLIER_AID");
                supplierAid.setTextContent(product.getOrderNumberCustomer());
                article.appendChild(supplierAid);
            }

            // -----------------------------------------------------------------
            // Article Details
            Element articleDetails = doc.createElement("ARTICLE_DETAILS");

            // Short Description
            Element descriptionShort = doc.createElement("DESCRIPTION_SHORT");
            descriptionShort.setTextContent(product.getShortDescription());
            articleDetails.appendChild(descriptionShort);

            // Long description
            Element descriptionLong = doc.createElement("DESCRIPTION_LONG");
            descriptionLong.setTextContent(product.getLongDescription());
            articleDetails.appendChild(descriptionLong);

            Element ean = doc.createElement("EAN");
            descriptionLong.setTextContent(product.getOrderNumberCustomer());
            articleDetails.appendChild(ean);

            article.appendChild(articleDetails);

            // -----------------------------------------------------------------
            // Article Order Details
            Element articleOrderDetails = doc.createElement("ARTICLE_ORDER_DETAILS");
            Element orderUnit = doc.createElement("ORDER_UNIT");
            Element noCuPerOu = doc.createElement("NO_CU_PER_OUT");
            orderUnit.setTextContent("C62");
            noCuPerOu.setTextContent("1");
            articleOrderDetails.appendChild(orderUnit);
            articleOrderDetails.appendChild(noCuPerOu);

            article.appendChild(articleOrderDetails);

            // -----------------------------------------------------------------
            // Price Details
            List<BOSalesPrice> salesPrices = PriceBOA.getInstance().findSalesPrices(product);
            Element priceDetails = doc.createElement("ARTICLE_PRICE_DETAILS");
            for (BOSalesPrice salesPrice : salesPrices) {
                Element articlePrice = doc.createElement("ARTICLE_PRICE");
                Element priceAmount = doc.createElement("PRICE_AMOUNT");
                Element currency = doc.createElement("PRICE_CURRENCY");
                Element tax = doc.createElement("TAX");
                Element priceTerritory = doc.createElement("TERRITORY");

                articlePrice.appendChild(priceAmount);
                articlePrice.appendChild(currency);
                articlePrice.appendChild(tax);
                articlePrice.appendChild(priceTerritory);

                priceAmount.setTextContent(salesPrice.getAmount().toString());
                currency.setTextContent(salesPrice.getCountry().getCurrency().getCode());
                tax.setTextContent(salesPrice.getTaxrate().toString());
                priceTerritory.setTextContent(salesPrice.getCountry().getIsocode());

                priceDetails.appendChild(articlePrice);

            }
            article.appendChild(priceDetails);

            tNewCatalog.appendChild(article);

        }

        return tNewCatalog;
    }

}
