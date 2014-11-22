<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<% String message = request.getParameter("nofile");%>


<html>
    <head>
        <title>eBusiness Framework Demo - Import</title>
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="pragma" content="no-cache">
        <link rel="stylesheet" type="text/css" href="default.css">
        <script src="js/jquery-2.1.1.min.js" type="text/javascript"></script>

        <script type="text/javascript">
            // Clientside check if a file was chosen, this starts befor the form will be send
            function checkUpload() {
                if ($("#xmlfile").val() === "") {
                    // Red boarder around the input field
                    $("#xmlfile").css("border", "solid 2px red");
                    // altert to inform the user that he has to choose a file
                    alert("No file chosen, Please first choose a File an then try uploading again");
                    return false;
                }
                return true;
            }
        </script>

    </head>
    <body>

        <!-- Standard Imports to generate the GUI-->
        <%@ include file="header.jsp" %>
        <%@ include file="error.jsp" %>
        <%@ include file="authentication.jsp" %>
        <%@ include file="navigation.jspfragment" %>

        <h1>XML-Import</h1>
        <div>

            <p>Please choose your XML-File:<br>
                <% if (message != null) {
                        out.write(message);
                    }%></p>
            <!-- XML - Upload Form-->
            <form action="controllerservlet?action=ImportXML" enctype="multipart/form-data" method="post" onSubmit="return checkUpload();">
                <label>File upload</label>
                <input type="file" name="xmlfile" id="xmlfile" />
                <input type="submit" value="Upload" name="upload" id="upload" required/>
            </form>
        </div>
    </body>
</html>
