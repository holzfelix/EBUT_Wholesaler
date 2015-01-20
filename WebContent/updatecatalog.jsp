<%@page import="java.util.List"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <div>
            <!-- XML - Upload Form-->
            <form action="controllerservlet?action=ImportXML" enctype="multipart/form-data" method="post" onSubmit="return checkUpload();">
                <label>File upload</label>
                <input type="file" name="xmlfile" id="xmlfile" />
                <input type="submit" value="Upload" name="upload" id="upload" required/>
            </form>
        </div>
    </body>
</html>
