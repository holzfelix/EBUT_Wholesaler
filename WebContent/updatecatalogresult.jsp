<%@ page session="true" import="de.htwg_konstanz.ebus.framework.wholesaler.api.bo.*,de.htwg_konstanz.ebus.framework.wholesaler.api.boa.*,java.math.BigDecimal,de.htwg_konstanz.ebus.framework.wholesaler.vo.util.PriceUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<% String infomessage = request.getParameter("infomessage");
    String errormessage = request.getParameter("errormessage");
    String notimported = request.getParameter("notimported");%>


<html>
    <head>
        <title>updateCatalaog Webservice</title>
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="pragma" content="no-cache">
        <link rel="stylesheet" type="text/css" href="default.css">
        <script src="js/jquery-2.1.1.min.js" type="text/javascript"></script>
    </head>
    <body>

        <!-- Standard Imports to generate the GUI-->
        <%@ include file="header.jsp" %>
        <%@ include file="error.jsp" %>
        <%@ include file="authentication.jsp" %>
        <%@ include file="navigation.jspfragment" %>

        <h1>updateCatalaog Webservice</h1>
        <p><%= request.getParameter("infomessage")%> </p>

    </body>
</html>
