<%-- 
    Document   : home
    Created on : Apr 16, 2015, 3:35:51 AM
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
                                <a href="${pageContext.request.contextPath}/home.htm">Home</a>
                            </li>
                        </ul>
                    </div>

                    <div class="sortable row-fluid">
                        
                        <div class="row-fluid sortable">
                        <div class="box span12">
                            <div class="box-header well" data-original-title>
                                <h2><i class="icon-edit"></i> Device Requests</h2>
                            </div>
                            <div class="box-content">

                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Request ID</th>
                                            <th>Sent By</th>
                                            <th>Hospital</th>
                                            <th>Status</th>                                         
                                            <th>Created On</th>  
                                            <th>Processed By</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <c:forEach var="request" items="${requestScope.deviceRequestList}">
                                            <tr>
                                                <td><a href="${pageContext.request.contextPath}/device/requests/process.htm?rid=${request.id}">${request.number}</td>   
                                                <td>${request.createdBy.firstName}&nbsp;${request.createdBy.lastName}</td>  
                                                <td>${request.createdBy.enterpriseBean.name}</td>  
                                                <td>${requestMap.get(request.statusId)}</td>  
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

            </div>

            <hr/>

            <%@include file="../../../../jsp/include/footer.jsp" %>

        </div>

    </body>


</html>





