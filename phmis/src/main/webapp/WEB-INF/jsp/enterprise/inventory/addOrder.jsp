<%-- 
    Document   : addOrder
    Created on : Apr 24, 2015, 3:49:55 AM
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
                                <a href="${pageContext.request.contextPath}/device/requests/process.htm?rid=${request.id}">Request</a> 
                            </li>
                        </ul>
                    </div>

                    <div class="row-fluid sortable">
                        <div class="box span12">
                            <div class="box-header well" data-original-title>
                                <h2><i class="icon-edit"></i> Device Requests</h2>
                            </div>
                            <div class="box-content">
                                <form action="${pageContext.request.contextPath}/order/create.htm" method="POST" class="form-horizontal">

                                    <input type="hidden" name="requestId" value="${request.id}"/>

                                    <div id="error-div" style="display: none">
                                        <div class="box-content alerts" id="popup">
                                            <div class="alert alert-error">
                                                <strong><span id="error"></span></strong>
                                            </div>
                                        </div>
                                    </div>

                                    <fieldset>

                                        <p>Enter the Quantity for each product</p>

                                        <c:forEach var="product" items="${productList}">
                                            <div class="control-group">
                                                <label class="control-label" for="statusId">${product.name}</label>
                                                <div class="controls">
                                                    <input type="number" id="statusId" name="${product.id}" class="input-mini" value="1"/>
                                                </div>
                                            </div>
                                        </c:forEach>

                                        <div class="form-actions">
                                            <input type="submit" class="btn btn-primary" value="Order"/>
                                            <a href="${pageContext.request.contextPath}/home.htm" class="btn">Back</a>
                                        </div>

                                    </fieldset>

                                </form>

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










