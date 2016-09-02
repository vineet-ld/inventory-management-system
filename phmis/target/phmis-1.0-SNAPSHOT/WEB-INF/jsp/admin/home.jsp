<%-- 
    Document   : home
    Created on : Apr 2, 2015, 8:15:00 PM
    Author     : Vineet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
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
                                <a href="${pageContext.request.contextPath}/admin/home.htm">Home</a>
                            </li>
                        </ul>
                    </div>

                    <%--<c:if test='${requestScope.FirstLogin != null}'>
                        <div>
                            <div class="box-content alerts" id="popup">
                                <div class="alert alert-success">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    <strong>Welcome ${sessionScope.user}</strong>
                                </div>
                            </div>
                        </div>
                    </c:if>--%>

                    <div class="row-fluid sortable">
                        <div class="box span12">
                            <div class="box-header well" data-original-title>
                                <h2><i class="icon-edit"></i> Activity Logs</h2>
                            </div>
                            <div class="box-content">

                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Message ID</th>
                                            <th>Sender</th>
                                            <th>Message</th>                                         
                                            <th>Date</th>                                         
                                            <th>Action</th>                                         
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <%--<c:forEach var="message" items="${requestScope.MessageList}">
                                            <tr>
                                                <td>${message.messageId}</td>                                      
                                                <td>${message.fromUser}</td>                                      
                                                <td>${message.message}</td>                                      
                                                <td>${message.messageDate}</td>                                      
                                                <td>
                                                    <a href="messages/reply.htm?mid=${message.messageId}" title="Reply"><span class="icon32 icon-color icon-reply"></span></a>
                                                    &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
                                                    <a href="messages/delete.htm?mid=${message.messageId}" title="Delete"><span class="icon32 icon-color icon-trash"></span></a>
                                                </td>                                      
                                            </tr>
                                        </c:forEach>--%>

                                    </tbody>
                                </table>

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




