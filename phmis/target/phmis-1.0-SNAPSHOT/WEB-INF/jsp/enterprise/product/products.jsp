<%-- 
    Document   : products
    Created on : Apr 18, 2015, 10:26:12 AM
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
                                <a href="${pageContext.request.contextPath}/home.htm">Home</a><span class="divider">/</span>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/products.htm">Products</a>
                            </li>
                        </ul>
                    </div>

                    <c:if test='${requestScope.productAdded != null && requestScope.productAdded == true}'>
                        <div>
                            <div class="box-content alerts" id="popup">
                                <div class="alert alert-success">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    <strong>New User has been added.</strong>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <c:if test='${requestScope.productAdded != null && requestScope.productAdded == false}'>
                        <div>
                            <div class="box-content alerts" id="popup">
                                <div class="alert alert-error">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    <strong>Could not add User. User name already in use.</strong>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <div class="row-fluid sortable">
                        <div class="box span12">
                            <div class="box-header well" data-original-title>
                                <h2><i class="icon-edit"></i> Products</h2>
                                <div class="box-icon">
                                    <a href="#" class="btn btn-setting btn-round" title="Add Product"><i class="icon-plus"></i></a>
                                </div>
                            </div>
                            <div class="box-content">

                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Product ID</th>
                                            <th>Name</th>
                                            <th>Description</th>                                         
                                            <th>Date Added</th>                                           
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <c:forEach var="product" items="${productList}">
                                            <tr>
                                                <td>${product.number}</td>                                      
                                                <td>${product.name}</td>                                      
                                                <td><a href="#" data-rel="popover" data-content="${product.description}"><span class="icon32 icon-color icon-info"></span></a></td>                                      
                                                <td>${product.additionDate}</td>                                       
                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                </table>

                            </div>
                        </div>

                    </div>

                </div>

            </div>

            <hr/>

            <div class="modal hide fade" id="myModal">

                <spring:form action="${pageContext.request.contextPath}/products/add.htm" commandName="product" method="POST" cssClass="form-horizontal" onsubmit="return validateProduct();">

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">×</button>
                        <h3>Add New Product</h3>
                    </div>

                    <div class="modal-body">

                        <div id="error-div" style="display: none">
                            <div class="box-content alerts" id="popup">
                                <div class="alert alert-error">
                                    <strong><span id="error"></span></strong>
                                </div>
                            </div>
                        </div>

                        <fieldset>

                            <div class="control-group">
                                <label class="control-label" for="name">Name:</label>
                                <div class="controls">
                                    <spring:input  cssClass="input-xlarge" path="name" id="name"/>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="name">Cost:</label>
                                <div class="controls">
                                    <spring:input  cssClass="input-xlarge" path="cost" id="cost"/>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="description">Description:</label>
                                <div class="controls">
                                    <spring:textarea  cssClass="autogrow" path="description" id="description"/>
                                </div>
                            </div>

                        </fieldset>

                    </div> 

                    <div class="modal-footer">
                        <input type="submit" class="btn btn-primary" value="Add"/>
                        <input type="reset" class="btn" data-dismiss="modal" value="Cancel"/>
                    </div>

                </spring:form>
            </div>

            <%@include file="../../../../jsp/include/footer.jsp" %>

        </div>

    </body>


</html>





