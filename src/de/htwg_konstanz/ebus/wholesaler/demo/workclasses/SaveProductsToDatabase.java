/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg_konstanz.ebus.wholesaler.demo.workclasses;

import de.htwg_konstanz.ebus.framework.wholesaler.api.bo.BOCountry;
import de.htwg_konstanz.ebus.framework.wholesaler.api.bo.BOProduct;
import de.htwg_konstanz.ebus.framework.wholesaler.api.bo.BOPurchasePrice;
import de.htwg_konstanz.ebus.framework.wholesaler.api.bo.BOSalesPrice;
import de.htwg_konstanz.ebus.framework.wholesaler.api.bo.BOSupplier;
import de.htwg_konstanz.ebus.framework.wholesaler.api.boa.CountryBOA;
import de.htwg_konstanz.ebus.framework.wholesaler.api.boa.PriceBOA;
import de.htwg_konstanz.ebus.framework.wholesaler.api.boa.ProductBOA;
import de.htwg_konstanz.ebus.framework.wholesaler.api.boa.SupplierBOA;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Felix
 */
public final class SaveProductsToDatabase {

    /**
     * Private instanz Variable.
     */
    private static SaveProductsToDatabase instance;

    /**
     * List with the not imported Products.
     */
    private List<String> noImported;

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

    /**
     * Reads the XML file an persistates the new products.
     *
     * @param xmlFile org.w3c.dom.Document
     * @return ReportDTO
     */
    public ReportDTO readXML(final org.w3c.dom.Document xmlFile) {
        NodeList nodes = xmlFile.getElementsByTagName("ARTICLE");
        NodeList supplierNodes = xmlFile.getElementsByTagName("SUPPLIER");
        Node nodeSup = supplierNodes.item(0);
        Element supplierName = (Element) nodeSup;

        ReportDTO dto = new ReportDTO(true, "");

        // initializing the Array of not imported Products
        this.noImported = new ArrayList<>();
        int productCounter = 0;

        for (int temp = 0; temp < nodes.getLength(); temp++) {
            Node node = nodes.item(temp);
            System.out.println("\nCurrent Element :" + node.getNodeName());
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                productCounter++;

                Element eElement = (Element) node;

                // Colsole out to check the values
                System.out.println("Supplier_Aid: " + eElement.getElementsByTagName("SUPPLIER_AID").item(0).getTextContent());
                System.out.println("DESCRIPTION_SHORT: " + eElement.getElementsByTagName("DESCRIPTION_SHORT").item(0).getTextContent());
                System.out.println("DESCRIPTION_LONG: " + eElement.getElementsByTagName("DESCRIPTION_LONG").item(0).getTextContent());
                System.out.println("EAN: " + eElement.getElementsByTagName("EAN").item(0).getTextContent());
                System.out.println("ORDER_UNIT: " + eElement.getElementsByTagName("ORDER_UNIT").item(0).getTextContent());
                System.out.println("NO_CU_PER_OU: " + eElement.getElementsByTagName("NO_CU_PER_OU").item(0).getTextContent());

                // Generating a new Product
                BOProduct product;
                product = new BOProduct();
                product.setLongDescription(eElement.getElementsByTagName("DESCRIPTION_LONG").item(0).getTextContent());
                product.setLongDescriptionCustomer(eElement.getElementsByTagName("DESCRIPTION_LONG").item(0).getTextContent());
                product.setShortDescription(eElement.getElementsByTagName("DESCRIPTION_SHORT").item(0).getTextContent());
                product.setShortDescriptionCustomer(eElement.getElementsByTagName("DESCRIPTION_SHORT").item(0).getTextContent());
                product.setOrderNumberCustomer(eElement.getElementsByTagName("SUPPLIER_AID").item(0).getTextContent());
                product.setOrderNumberSupplier(eElement.getElementsByTagName("SUPPLIER_AID").item(0).getTextContent());

                // Read suppliername form XML
                String foundSupplierName = supplierName.getElementsByTagName("SUPPLIER_NAME").item(0).getTextContent();
                System.out.println("SupplierName: " + foundSupplierName);

                SupplierBOA foundSuppliers = SupplierBOA.getInstance();
                BOSupplier supplier = new BOSupplier();
                // Search supplier
                List supllierlist = foundSuppliers.findByCompanyName(foundSupplierName);

                // if not found create a new Supplier
                if (supllierlist.isEmpty()) {
                    dto.setType(false);
                    dto.setMessage("Sorry Supplier not found in Database. Please insert supplier first manually and try again!");
                    return dto;
                } else {
                    supplier = (BOSupplier) supllierlist.get(0);
                }
                // Set supplier for product
                product.setSupplier(supplier);

                // Check if product already is persitent in database, if ture abort the import and send feedback
                BOProduct check = ProductBOA.getInstance().findByOrderNumberCustomer(product.getOrderNumberCustomer());

                System.out.println(check);

                if (check != null) {
                    System.out.println("------------------- Produkt already in DB");
                    noImported.add(product.getOrderNumberCustomer());
                    continue;
                }

                // persistating product
                ProductBOA.getInstance().saveOrUpdate(product);

                // Preise
                NodeList preise = eElement.getElementsByTagName("ARTICLE_PRICE");
                for (int z = 0; z < preise.getLength(); z++) {

                    Node preis = preise.item(z);
                    Element preisElement = (Element) preis;

                    // Console out to check the values
                    System.out.println("--------- PREIS:");
                    System.out.println("Preis_Type: " + preisElement.getAttribute("price_type"));
                    System.out.println("PRICE_AMOUNT: " + preisElement.getElementsByTagName("PRICE_AMOUNT").item(0).getTextContent());
                    System.out.println("PRICE_CURRENCY: " + preisElement.getElementsByTagName("PRICE_CURRENCY").item(0).getTextContent());
                    System.out.println("TAX: " + preisElement.getElementsByTagName("TAX").item(0).getTextContent());
                    System.out.println("TERRITORY: " + preisElement.getElementsByTagName("TERRITORY").item(0).getTextContent());

                    // Generating Sales Prcie ( Price * 20 percent)
                    BigDecimal amount = new BigDecimal(preisElement.getElementsByTagName("PRICE_AMOUNT").item(0).getTextContent());
                    BOSalesPrice salesPrice = new BOSalesPrice(increaseSalesPrice(amount), new BigDecimal(preisElement.getElementsByTagName("TAX").item(0).getTextContent()), preisElement.getAttribute("price_type"));

                    // Setting country
                    BOCountry country = null;
                    country = CountryBOA.getInstance().findCountry(preisElement.getElementsByTagName("TERRITORY").item(0).getTextContent());

                    salesPrice.setCountry(country);
                    salesPrice.setProduct(product);
                    salesPrice.setLowerboundScaledprice(1);

                    PriceBOA.getInstance().saveOrUpdateSalesPrice(salesPrice);

                    // Generating Purchase Price
                    BOPurchasePrice purchasePrice = new BOPurchasePrice(amount, new BigDecimal(preisElement.getElementsByTagName("TAX").item(0).getTextContent()), preisElement.getAttribute("price_type"));
                    purchasePrice.setCountry(country);
                    purchasePrice.setProduct(product);
                    purchasePrice.setLowerboundScaledprice(1);
                    PriceBOA.getInstance().saveOrUpdatePurchasePrice(purchasePrice);

                }

            }
        }

        // Check which message have to be send
        if (!noImported.isEmpty()) {
            dto.setMessage("Not all poducts imported. " + noImported.size() + " products have already been in database. New products: " + (productCounter - noImported.size()));
            dto.setNotImported(noImported);
            dto.setType(false);
        } else {
            dto.setMessage("All " + productCounter + " poducts successfully imported.");
            dto.setNotImported(noImported);
            dto.setType(true);
        }
        productCounter = 0;
        noImported = null;
        return dto;
    }

    /**
     * Price * 20%.
     *
     * @param amount BigDecimal
     * @return BigDecimal
     */
    private BigDecimal increaseSalesPrice(final BigDecimal amount) {
        return amount.add(amount.multiply(new BigDecimal(0.2)));
    }
}
