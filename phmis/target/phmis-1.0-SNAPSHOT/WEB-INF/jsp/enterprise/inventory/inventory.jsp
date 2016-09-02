<%-- 
    Document   : inventory
    Created on : Apr 24, 2015, 8:42:20 AM
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
                                <a href="${pageContext.request.contextPath}/inventory.htm">Inventory</a> 
                            </li>
                        </ul>
                    </div>

                    <div class="sortable row-fluid">
                        
                        <div class="row-fluid sortable">
                        <div class="box span12">
                            <div class="box-header well" data-original-title>
                                <h2><i class="icon-edit"></i> Inventory</h2>
                            </div>
                            <div class="box-content">

                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Item Code</th>
                                            <th>Product</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <c:forEach var="item" items="${requestScope.itemList}">
                                            <tr> 
                                                <td>${item.itemCode}</td>  
                                                <td>${item.productBean.name}</td>
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






