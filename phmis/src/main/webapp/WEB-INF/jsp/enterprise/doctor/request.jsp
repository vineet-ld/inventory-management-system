<%-- 
    Document   : request
    Created on : Apr 22, 2015, 1:12:56 PM
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
                                <a href="${pageContext.request.contextPath}/surgery/requests.htm">Requests</a> 
                            </li>
                        </ul>
                    </div>

                    <c:if test='${requestScope.requestCreated != null && requestScope.requestCreated == true}'>
                        <div>
                            <div class="box-content alerts" id="popup">
                                <div class="alert alert-success">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    <strong>A new surgery request has been created.</strong>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <div class="row-fluid sortable">
                        <div class="box span12">
                            <div class="box-header well" data-original-title>
                                <h2><i class="icon-edit"></i> Surgery Requests</h2>
                                <div class="box-icon">
                                    <a href="#" class="btn btn-setting btn-round" title="Schedule Surgery"><i class="icon-plus"></i></a>
                                </div>
                            </div>
                            <div class="box-content">

                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Request ID</th>
                                            <th>Status</th>                                         
                                            <th>Created On</th>  
                                            <th>Processed By</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <c:forEach var="request" items="${requestScope.requestList}">
                                            <tr>
                                                <td><a href="${pageContext.request.contextPath}/surgery/requests/details.htm?rid=${request.id}">${request.number}</td>   
                                                <td>${requestStatus.get(request.statusId)}</td>  
                                                <td>${request.creationDate}</td>  
                                                <td>${request.processedBy}</td>
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

                <spring:form action="${pageContext.request.contextPath}/surgery/requests/create.htm" commandName="surgeryRequest" method="POST" cssClass="form-horizontal" onsubmit="return validateSurgeryRequest();">

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">×</button>
                        <h3>Schedule Surgery</h3>
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
                                <label class="control-label" for="dateFrom">From Date:</label>
                                <div class="controls">
                                    <spring:input type="text" class="input-xlarge datepicker" path="dateFrom" id="dateFrom"/>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="dateTo">To Date:</label>
                                <div class="controls">
                                    <spring:input type="text" class="input-xlarge datepicker" path="dateTo" id="dateTo"/>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="staffCount">Total Staff:</label>
                                <div class="controls">
                                    <spring:input  cssClass="input-mini" path="staffCount" id="staffCount"/>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="requestedProducts">Devices</label>
                                <div class="controls">
                                    <select id="requestedProducts" multiple name="products" data-rel="chosen">
                                        <c:forEach var="product" items="${productList}">
                                            <option value="${product.id}">${product.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="type">Surgery Type:</label>
                                <div class="controls">
                                    <input type="text"  class="input-xlarge" name="type" id="type"/>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="patientName">Patient Name:</label>
                                <div class="controls">
                                    <input type="text" class="input-xlarge" name="patientName" id="patientName"/>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="specialInstructions">Special Instructions:</label>
                                <div class="controls">
                                    <spring:textarea  cssClass="autogrow" path="specialInstructions" id="specialInstructions"/>
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






