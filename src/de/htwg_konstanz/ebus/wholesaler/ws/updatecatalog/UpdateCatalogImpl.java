/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg_konstanz.ebus.wholesaler.ws.updatecatalog;

import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;

/**
 *
 * @author Felix
 */
@ServiceMode(value = javax.xml.ws.Service.Mode.PAYLOAD)
@WebServiceProvider(serviceName = "UpdateCatalogWebService", portName = "updateCatalogPort", targetNamespace = "http://localhost:8084/EBUT_Wholesaler/updatecatalog", wsdlLocation = "WEB-INF/wsdl/UpdateCatalogImpl/updateCatalogue.wsdl")
public class UpdateCatalogImpl implements javax.xml.ws.Provider<javax.xml.transform.Source>, UpdateCatalog {

    public javax.xml.transform.Source invoke(javax.xml.transform.Source source) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public UpdateResponse updateCatalog(UpdateRequest updateRequest) throws AuthenticationFault {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
