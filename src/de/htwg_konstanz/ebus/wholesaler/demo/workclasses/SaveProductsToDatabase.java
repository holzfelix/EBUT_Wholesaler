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
import de.htwg_konstanz.ebus.framework.wholesaler.api.boa.CountryBOA;
import de.htwg_konstanz.ebus.framework.wholesaler.api.boa.PriceBOA;
import de.htwg_konstanz.ebus.framework.wholesaler.api.boa.ProductBOA;
import java.math.BigDecimal;
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
     * Constant für the int value 20.
     */
    private static final int TWENTY = 20;

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
     */
    public void readXML(final org.w3c.dom.Document xmlFile) {
        NodeList nodes = xmlFile.getElementsByTagName("ARTICLE");

        for (int temp = 0; temp < nodes.getLength(); temp++) {
            Node node = nodes.item(temp);
            System.out.println("\nCurrent Element :" + node.getNodeName());
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) node;

                // Colsole out to check the values
                System.out.println("Supplier_Aid: " + eElement.getElementsByTagName("SUPPLIER_AID").item(0).getTextContent());
                System.out.println("DESCRIPTION_SHORT: " + eElement.getElementsByTagName("DESCRIPTION_SHORT").item(0).getTextContent());
                System.out.println("DESCRIPTION_LONG: " + eElement.getElementsByTagName("DESCRIPTION_LONG").item(0).getTextContent());
                System.out.println("EAN: " + eElement.getElementsByTagName("EAN").item(0).getTextContent());
                System.out.println("ORDER_UNIT: " + eElement.getElementsByTagName("ORDER_UNIT").item(0).getTextContent());
                System.out.println("CONTENT_UNIT: " + eElement.getElementsByTagName("CONTENT_UNIT").item(0).getTextContent());
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

                // persistating product
                ProductBOA.getInstance().saveOrUpdate(product);

            }
        }

    }

    /**
     * Price * 20%.
     *
     * @param amount BigDecimal
     * @return BigDecimal
     */
    private BigDecimal increaseSalesPrice(final BigDecimal amount) {
        return amount.multiply(new BigDecimal(TWENTY));
    }
}
