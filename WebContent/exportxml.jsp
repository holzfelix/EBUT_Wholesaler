<%@ page session="true" import="de.htwg_konstanz.ebus.framework.wholesaler.api.bo.*,de.htwg_konstanz.ebus.framework.wholesaler.api.boa.*,java.math.BigDecimal,de.htwg_konstanz.ebus.framework.wholesaler.vo.util.PriceUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<% String infomessage = request.getParameter("infomessage");
    String errormessage = request.getParameter("errormessage");%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
    <head>
        <title>eBusiness Framework Demo - Export</title>
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="pragma" content="no-cache">
        <link rel="stylesheet" type="text/css" href="default.css">

    </head>
    <body>

        <%@ include file="header.jsp" %>
        <%@ include file="error.jsp" %>
        <%@ include file="authentication.jsp" %>
        <%@ include file="navigation.jspfragment" %>

        <h1>XML-Export</h1>
        <p>
            <span class="info"><% if (infomessage != null) {
                    out.write(infomessage);
                }%></span>
            <span class="error">
                <% if (errormessage != null) {
                        out.write(errormessage);
                    }%></span>
        </p>
        <div>
            <form action="controllerservlet?action=ExportXML" method="post">
                <p>Enter a Short-Description to export. (optional) If no Short-Description is given, the whole catalogue will be exported.</p>
                Short Description: <input type="text" name="substring" id="substring" /><br><br>
                BMEcat: <input type="radio" name="exportType" value="BMEcat"><br>
                XHTML: <input type="radio" name="exportType" value="XHTML"><br><br>
                <input type="submit" name="submit" value="Abrufen" />
            </form>
        </div>
    </body>
</html>
