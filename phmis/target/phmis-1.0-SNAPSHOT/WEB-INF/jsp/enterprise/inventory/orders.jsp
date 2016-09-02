<%-- 
    Document   : orders
    Created on : Apr 24, 2015, 6:31:27 AM
    Author     : Vineet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="../../../../jsp/include/commonTags.jsp" %>
    </head>
    <body>

        <%@include file="../../../../jsp/include/header.jsp" %>

        <div class="container-fluid">
            <div class="row-fluid">

                <div class="span2 main-menu-span">
                    <div class="well nav-collapse sidebar-nav">
                        <ul class="nav nav-tabs nav-stacked main-menu">
                            <%@include file="menu.jsp" %>
                        </ul>
                    </div>
                </div>


                <div id="content" class="span10">

                    <div>
                        <ul class="breadcrumb">
                            <li>
                                <a href="${pageContext.request.contextPath}/home.htm">Home</a> <span class="divider">/</span>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/inventory/orders.htm">Orders</a> 
                            </li>
                        </ul>
                    </div>

                    <div class="sortable row-fluid">

                        <div class="row-fluid sortable">
                            <div class="box span12">
                                <div class="box-header well" data-original-title>
                                    <h2><i class="icon-edit"></i> Orders</h2>
                                </div>
                                <div class="box-content">

                                    <table class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th>Order ID</th>
                                                <th>Ordered From</th>
                                                <th>Order Date</th>
                                            </tr>
                                        </thead>

                                        <tbody>
                                            <c:forEach var="order" items="${requestScope.orderList}">
                                                <tr>
                                                    <td><a href="${pageContext.request.contextPath}/order/details.htm?oid=${order.id}">${order.number}</td>   
                                                    <td>${order.orderedFrom.name}</td>  
                                                    <td>${order.creationDate}</td>  
                                                </tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>

                                </div>
                            </div>

                        </div>

                    </div>

                </div>

            </div>

            <hr/>

            <%@include file="../../../../jsp/include/footer.jsp" %>

        </div>

    </body>


</html>






