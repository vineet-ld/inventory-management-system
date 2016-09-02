<%-- 
    Document   : orderDetails
    Created on : Apr 24, 2015, 7:06:11 AM
    Author     : Vineet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix = "spring" %>
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
                                <a href="${pageContext.request.contextPath}/inventory/orders.htm">Orders</a> <span class="divider">/</span>
                            </li>
                            <li>
                                <a href="#">Details</a> 
                            </li>
                        </ul>
                    </div>

                    <div class="row-fluid sortable">
                        <div class="box span12">
                            <div class="box-header well" data-original-title>
                                <h2><i class="icon-edit"></i> Order Details</h2>
                            </div>
                            <div class="box-content">
                                <p><b>ID: </b>${order.number}</p>
                                <p><b>Ordered From: </b>${order.orderedFrom.name}</p>
                                <p><b>Order Date: </b>${order.creationDate}</p>
                                <p><b>Devices Requested:</b></p>

                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Product</th>
                                            <th>Price</th>
                                            <th>Quantity</th>
                                            <th>Total</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <c:forEach var="orderItem" items="${order.orderItemList}">
                                            <tr>  
                                                <td>${orderItem.productBean.name}</td>  
                                                <td>$ ${orderItem.productBean.cost}</td>  
                                                <td>${orderItem.quantity}</td>
                                                <td>${orderItem.quantity * orderItem.productBean.cost}</td>
                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                </table>

                                <c:if test="${order.addedToInventory == 0}">
                                    <div class="form-actions">
                                        <a href="${pageContext.request.contextPath}/inventory/items/add.htm?oid=${order.id}" class="btn btn-primary">Add to Inventory</a>
                                    </div>
                                </c:if>

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








