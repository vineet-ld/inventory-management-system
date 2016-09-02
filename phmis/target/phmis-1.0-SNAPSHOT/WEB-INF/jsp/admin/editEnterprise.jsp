<%-- 
    Document   : editEnterprise
    Created on : Apr 10, 2015, 12:12:05 AM
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
                                <c:if test='${enterprise.typeId == 1}'>
                                    <a href="${pageContext.request.contextPath}/admin/hospitals.htm">Hospitals</a> <span class="divider">/</span>
                                </c:if>
                                <c:if test='${enterprise.typeId == 2}'>
                                    <a href="${pageContext.request.contextPath}/admin/warehouses.htm">Warehouses</a> <span class="divider">/</span>
                                </c:if>
                                <c:if test='${enterprise.typeId == 3}'>
                                    <a href="${pageContext.request.contextPath}/admin/vendors.htm">Vendors</a> <span class="divider">/</span>
                                </c:if>                                
                            </li>
                            <li>
                                <a href="#">Edit</a>
                            </li>
                        </ul>
                    </div>

                    <div class="row-fluid sortable">
                        <div class="box span12">
                            <div class="box-header well" data-original-title>
                                <h2><i class="icon-edit"></i> Edit Enterprise Details</h2>
                            </div>
                            <div class="box-content">

                                <spring:form action="${pageContext.request.contextPath}/admin/hospitals/edit.htm" commandName="enterprise" method="POST" cssClass="form-horizontal" onsubmit="return validateEnterprise();">

                                    <fieldset>
                                        
                                        <spring:hidden path="id"/>
                                        <spring:hidden path="number"/>
                                        <spring:hidden path="typeId"/>

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

                                        <div class="form-actions">
                                            <input type="submit" class="btn btn-primary" value="Update"/>
                                            <c:if test='${enterprise.typeId == 1}'>
                                                <a class="btn" href="${pageContext.request.contextPath}/admin/hospitals.htm">Cancel</a>
                                            </c:if>
                                            <c:if test='${enterprise.typeId == 2}'>
                                                <a class="btn" href="${pageContext.request.contextPath}/admin/warehouses.htm">Cancel</a>
                                            </c:if>
                                            <c:if test='${enterprise.typeId == 3}'>
                                                <a class="btn" href="${pageContext.request.contextPath}/admin/vendors.htm">Cancel</a>
                                            </c:if> 
                                        </div>

                                    </fieldset>

                                </spring:form>                                

                            </div>
                        </div>

                    </div>

                </div>

            </div>

            <hr/>

            <%@include file="../../../jsp/include/footer.jsp" %>

        </div>

    </body>


</html>






