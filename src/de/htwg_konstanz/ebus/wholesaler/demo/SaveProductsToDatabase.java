/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg_konstanz.ebus.wholesaler.demo;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
        NodeList nodes = xmlFile.getElementsByTagName("ARTICLE");

        for (int temp = 0; temp < nodes.getLength(); temp++) {
            Node node = nodes.item(temp);
            System.out.println("\nCurrent Element :" + node.getNodeName());
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) node;
                System.out.println("Supplier_Aid: " + eElement.getElementsByTagName("SUPPLIER_AID").item(0).getTextContent());
                System.out.println("DESCRIPTION_SHORT: " + eElement.getElementsByTagName("DESCRIPTION_SHORT").item(0).getTextContent());
                System.out.println("DESCRIPTION_LONG: " + eElement.getElementsByTagName("DESCRIPTION_LONG").item(0).getTextContent());
                System.out.println("EAN: " + eElement.getElementsByTagName("EAN").item(0).getTextContent());
                System.out.println("ORDER_UNIT: " + eElement.getElementsByTagName("ORDER_UNIT").item(0).getTextContent());
                System.out.println("CONTENT_UNIT: " + eElement.getElementsByTagName("CONTENT_UNIT").item(0).getTextContent());
                System.out.println("NO_CU_PER_OU: " + eElement.getElementsByTagName("NO_CU_PER_OU").item(0).getTextContent());

                // Preise
                NodeList preise = eElement.getElementsByTagName("ARTICLE_PRICE");
                for (int z = 0; z < preise.getLength(); z++) {

                    Node preis = preise.item(z);
                    Element preisElement = (Element) preis;

                    System.out.println("--------- PREIS:");

                    System.out.println("Preis_Type: " + preisElement.getAttribute("price_type"));
                    System.out.println("PRICE_AMOUNT: " + preisElement.getElementsByTagName("PRICE_AMOUNT").item(0).getTextContent());
                    System.out.println("PRICE_CURRENCY: " + preisElement.getElementsByTagName("PRICE_CURRENCY").item(0).getTextContent());
                    System.out.println("TAX: " + preisElement.getElementsByTagName("TAX").item(0).getTextContent());
                    System.out.println("TERRITORY: " + preisElement.getElementsByTagName("TERRITORY").item(0).getTextContent());

                }
            }
        }
    }
}
