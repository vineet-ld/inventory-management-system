<%-- 
    Document   : requestDetails
    Created on : Apr 23, 2015, 6:59:26 AM
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
                                <a href="${pageContext.request.contextPath}/surgery/requests.htm">Requests</a> <span class="divider">/</span>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/surgery/requests/details.htm?rid=${request.id}">Details</a> 
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







