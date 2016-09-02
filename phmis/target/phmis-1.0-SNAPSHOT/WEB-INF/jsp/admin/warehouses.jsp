<%-- 
    Document   : inventories
    Created on : Apr 9, 2015, 8:34:27 PM
    Author     : Vineet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix = "spring" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="../../../jsp/include/commonTags.jsp" %>
    </head>
    <body>

        <%@include file="header.jsp" %>

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
                                <a href="${pageContext.request.contextPath}/admin/home.htm">Home</a> <span class="divider">/</span>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/admin/warehouses.htm">Warehouses</a>
                            </li>
                        </ul>
                    </div>

                    <c:if test='${requestScope.enterpriseAdded != null && requestScope.enterpriseAdded == true}'>
                        <div>
                            <div class="box-content alerts" id="popup">
                                <div class="alert alert-success">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    <strong>New Warehouse has been added.</strong>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <c:if test='${requestScope.enterpriseAdded != null && requestScope.enterpriseAdded == false}'>
                        <div>
                            <div class="box-content alerts" id="popup">
                                <div class="alert alert-error">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    <strong>Could not add Warehouse. Either Warehouse with the same name exists in the system or a Warehouse has been added for the county you have selected.</strong>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <div class="row-fluid sortable">
                        <div class="box span12">
                            <div class="box-header well" data-original-title>
                                <h2><i class="icon-plus"></i> Warehouses</h2>
                                <div class="box-icon">
                                    <a href="#" class="btn btn-setting btn-round" title="Add New"><i class="icon-plus"></i></a>
                                </div>
                            </div>
                            <div class="box-content">

                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Warehouse ID</th>
                                            <th>Name</th>
                                            <th>County</th>                                          
                                            <th>Action</th>                                         
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <c:forEach var="enterprise" items="${requestScope.enterpriseList}">
                                            <tr>
                                                <td><a href="${pageContext.request.contextPath}/admin/warehouses/view.htm?eid=${enterprise.id}">${enterprise.number}</a></td>                                      
                                                <td>${enterprise.name}</td>                                      
                                                <td>${counties.get(enterprise.countyId)}</td>                                            
                                                <td>
                                                    <a href="${pageContext.request.contextPath}/admin/warehouses/edit.htm?eid=${enterprise.id}" title="Edit"><span class="icon icon-color icon-edit"></span></a>
                                                </td>                                      
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

                <spring:form action="${pageContext.request.contextPath}/admin/warehouses/add.htm" commandName="enterprise" method="POST" cssClass="form-horizontal" onsubmit="return validateEnterprise();">

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">×</button>
                        <h3>Add New Warehouse</h3>
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
                                <label class="control-label" for="countyId">County:</label>
                                <div class="controls">
                                    <spring:select id="countyId" path="countyId" items="${counties}"/>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="street">Street</label>
                                <div class="controls">
                                    <spring:input cssClass="input-xlarge" path="street" id="street"/>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="suite">Suite:</label>
                                <div class="controls">
                                    <spring:input cssClass="input-xlarge" path="suite" id="suite"/>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="city">City:</label>
                                <div class="controls">
                                    <spring:input cssClass="input-xlarge" path="city" id="city"/>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="state">State:</label>
                                <div class="controls">
                                    <spring:input cssClass="input-small" path="state" id="state" size="2"/>
                                </div>
                            </div>    

                            <div class="control-group">
                                <label class="control-label" for="zipCode">Zip Code:</label>
                                <div class="controls">
                                    <spring:input cssClass="input-xlarge" path="zipCode" id="zipCode"/>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="contact">Contact:</label>
                                <div class="controls">
                                    <spring:input cssClass="input-xlarge" path="contact" id="contact"/>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="url">Website URL:</label>
                                <div class="controls">
                                    <spring:input cssClass="input-xlarge" path="url" id="url"/>
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

            <%@include file="../../../jsp/include/footer.jsp" %>

        </div>

    </body>


</html>
