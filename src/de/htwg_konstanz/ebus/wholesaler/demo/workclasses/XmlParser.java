/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg_konstanz.ebus.wholesaler.demo.workclasses;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

/**
 *
 * @author Felix
 */
public final class XmlParser {

    private ParseReportDTO reportDto;
    private org.w3c.dom.Document doc;

    /**
     * Konstruktor.
     */
    public XmlParser() {
        reportDto = new ParseReportDTO(null, null);
    }

    /**
     * Validates the XML.
     *
     * @param is InputStream
     * @return boolean Validate Status of the XML.
     * @throws ParserConfigurationException Exception Handling.
     * @throws SAXException Exception Handling.
     * @throws IOException Exception Handling.
     */
    public ParseReportDTO validateTheXml(final InputStream is) throws IOException, SAXException, ParserConfigurationException {

        doc = parseXML(is);
        // If document couldn't be parsed return an empty doc
        if (doc == null) {
            reportDto.setDoc(null);
            return reportDto;
        }
        // if validation error return null
        if (!validateXML(doc)) {
            reportDto.setDoc(null);
            return reportDto;
        }
        reportDto.setDoc(doc);
        return reportDto;
    }

    /**
     * Parsing the XML Dokument from the InputStream.
     *
     * @param is InputStream
     * @return org.w3c.dom.Document
     * @throws ParserConfigurationException Exception Handling.
     * @throws SAXException Exception Handling.
     * @throws IOException Exception Handling.
     */
    private org.w3c.dom.Document parseXML(final InputStream is) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        try {
            org.w3c.dom.Document document = db.parse(is);
            return document;
        } catch (SAXException e) {
            reportDto.setMessage("XML was not wellformed " + e.getMessage());
            return null;
        }

    }

    /**
     * Checks the Wellformness of the selected XML File.
     *
     * @param dom org.w3c.dom.Document
     * @return boolean
     * @throws SAXException Exception Handling.
     * @throws IOException Exception Handling.
     */
    private boolean validateXML(final org.w3c.dom.Document dom) throws IOException, SAXException {

        // Shemafactory
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        // Gets the URL of the Shema file
        URL url = new URL("http://localhost:8084/EBUT_Wholesaler/temp/bmecat_new_catalog_1_2_simple_without_NS.xsd");

        // establish connection to Schema File
        URLConnection uc = url.openConnection();
        InputStreamReader input = new InputStreamReader(uc.getInputStream());

        // Loading the Schema file.
        Source schemaFile = new StreamSource(input);
        Schema schema = factory.newSchema(schemaFile);

        Validator validator = schema.newValidator();

        try {
            validator.validate(new DOMSource(dom));
            System.out.println("Your Document is valid.");
            reportDto.setMessage("Your Document is valid.");
            return true;
        } catch (SAXException e) {
            System.out.println("VALIDATON ERROR " + e);
            reportDto.setMessage("VALIDATON ERROR " + e.getMessage());
            return false;
        }
    }
}
