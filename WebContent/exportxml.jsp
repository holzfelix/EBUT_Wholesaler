<%@ page session="true" import="de.htwg_konstanz.ebus.framework.wholesaler.api.bo.*,de.htwg_konstanz.ebus.framework.wholesaler.api.boa.*,java.math.BigDecimal,de.htwg_konstanz.ebus.framework.wholesaler.vo.util.PriceUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
    <head>
        <title>eBusiness Framework Demo - Export</title>
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="pragma" content="no-cache">
        <link rel="stylesheet" type="text/css" href="default.css">
        <script src="js/jquery-2.1.1.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $("input[name='selectall']").click(function () {
                    if (!$(this).attr("checked")) {
                        $(this).parents("table")
                                .find("input:checkbox")
                                .attr("checked", "checked");
                    }
                    else {
                        $(this).parents("table")
                                .find("input:checkbox")
                                .attr('checked', false);
                    }
                });
            });
        </script>

    </head>
    <body>

        <%@ include file="header.jsp" %>
        <%@ include file="error.jsp" %>
        <%@ include file="authentication.jsp" %>
        <%@ include file="navigation.jspfragment" %>


        <h1>XML-Export</h1>
        <p>Please select one or more Products to export.</p>
        <div>
            <form action="ExoportXML" enctype="multipart/form-data" method="post">
                <table class="dataTable">
                    <thead>
                        <tr>
                            <th><b>Order No.</b></th>
                            <th><b>Title</b></th>
                            <th><input type="checkbox" name="selectall" value="0"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${sessionScope.productList}">
                            <jsp:useBean id="product" type="de.htwg_konstanz.ebus.framework.wholesaler.api.bo.BOProduct" />
                            <tr valign="top">
                                <td><a href="<%= response.encodeURL("controllerservlet?action=showProductDetail&orderNumberCustomer=" + product.getOrderNumberCustomer())%>"><%= product.getOrderNumberCustomer()%></a></td>
                                <td><%= product.getShortDescription()%></td>
                                <td ><input type="checkbox" name="<%= product.getOrderNumberCustomer()%>" value="<%= product.getOrderNumberCustomer()%>"><br></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </form>
        </div>
    </body>
</html>
