<%-- 
    Document   : processRequest
    Created on : Apr 23, 2015, 10:06:07 AM
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
                                <a href="${pageContext.request.contextPath}/resource/requests.htm">Requests</a> <span class="divider">/</span>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/surgery/requests/process.htm?rid=${request.id}">View</a> 
                            </li>
                        </ul>
                    </div>

                    <div class="row-fluid sortable">
                        <div class="box span12">
                            <div class="box-header well" data-original-title>
                                <h2><i class="icon-edit"></i> Surgery Requests</h2>
                            </div>
                            <div class="box-content">
                                <p><b>ID: </b>${request.number}</p>
                                <p><b>Status: </b>${requestStatus.get(request.statusId)}</p>
                                <p><b>Created By: </b>${request.createdBy.firstName}&nbsp;${request.createdBy.lastName}</p>
                                <c:if test="${request.processedBy != null}">
                                    <p><b>Processed By: </b>${request.processedBy.firstName}&nbsp;${request.processedBy.lastName}</p>
                                </c:if>
                                <c:if test="${!request.processComment.equals('')}">
                                    <p><b>Comments: </b>${request.processComment}</p>
                                </c:if>
                                <c:if test="${!request.processedDate != null}">
                                    <p><b>Processed On: </b>${request.processedDate}</p>
                                </c:if>
                                <p><b>Schedule Date Range: </b>From &nbsp; ${request.dateFrom}&nbsp; to &nbsp;${request.dateTo}</p>
                                <c:if test='${request.specialInstructions.equals("")}'>
                                    <p><b>Special Instructions: </b>${request.specialInstructions}</p>
                                </c:if>
                                <p><b>No. of Staff: </b>${request.staffCount}</p>
                                <p><b>Devices Requested:</b></p>
                                <c:forEach var="product" items="${request.requestedProducts}">
                                    <p>${product.name}</p>
                                </c:forEach>

                                <div class="modal-footer">
                                    <a href="#" class="btn btn-setting btn-primary">Process</a>
                                    <a href="#" class="btn">Back</a>
                                </div>

                            </div>
                        </div>

                    </div>

                </div>

            </div>

            <hr/>

            <div class="modal hide fade" id="myModal">
                <spring:form action="${pageContext.request.contextPath}/surgery/requests/process.htm" commandName="request" method="POST" cssClass="form-horizontal">

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

                            <spring:hidden path="id"/>

                            <div class="control-group">
                                <label class="control-label" for="statusId">Select Status</label>
                                <div class="controls">
                                    <spring:select id="statusId" path="statusId" items="${requestMap}"/>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="processComment">Comments:</label>
                                <div class="controls">
                                    <spring:textarea  cssClass="autogrow" path="processComment" id="processComment"/>
                                </div>
                            </div>

                        </fieldset>

                    </div> 

                    <div class="modal-footer">
                        <input type="submit" class="btn btn-primary" value="Process"/>
                        <input type="reset" class="btn" data-dismiss="modal" value="Cancel"/>
                    </div>

                </spring:form>
            </div>

            <%@include file="../../../../jsp/include/footer.jsp" %>


        </div>

    </body>


</html>








