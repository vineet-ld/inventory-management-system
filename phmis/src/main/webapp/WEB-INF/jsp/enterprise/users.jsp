<%-- 
    Document   : users
    Created on : Apr 18, 2015, 3:57:12 AM
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

        <%@include file="../../../jsp/include/header.jsp" %>

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
                                <a href="${pageContext.request.contextPath}/users.htm">Users</a>
                            </li>
                        </ul>
                    </div>

                    <c:if test='${requestScope.userAdded != null && requestScope.userAdded == true}'>
                        <div>
                            <div class="box-content alerts" id="popup">
                                <div class="alert alert-success">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    <strong>New User has been added.</strong>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <c:if test='${requestScope.userAdded != null && requestScope.userAdded == false}'>
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
                                <h2><i class="icon-user "></i> Users</h2>
                                <div class="box-icon">
                                    <a href="#" class="btn btn-setting btn-round" title="Add User"><i class="icon-plus"></i></a>
                                </div>
                            </div>
                            <div class="box-content">

                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>User ID</th>
                                            <th>Name</th>
                                            <th>Role</th>                                           
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <c:forEach var="user" items="${requestScope.users}">
                                            <tr>
                                                <td><a href="${pageContext.request.contextPath}/users/view.htm?uid=${user.id}">${user.number}</a></td>                                      
                                                <td>${user.firstName}&nbsp;${user.lastName}</td>                                      
                                                <td>${user.roleBean.name}</td>                                       
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

                <spring:form action="${pageContext.request.contextPath}/users/add.htm" commandName="user" method="POST" cssClass="form-horizontal" onsubmit="return validateUser();">

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">×</button>
                        <h3>Add New User</h3>
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
                                <label class="control-label" for="username">User Name:</label>
                                <div class="controls">
                                    <spring:input  cssClass="input-xlarge" path="username" id="username" value=""/>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="emailId">Email ID:</label>
                                <div class="controls">
                                    <spring:input  cssClass="input-xlarge" path="emailId" id="emailId" value=""/>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="firstName">First Name:</label>
                                <div class="controls">
                                    <spring:input cssClass="input-xlarge" path="firstName" id="firstName" value=""/>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="lastName">Last Name:</label>
                                <div class="controls">
                                    <spring:input cssClass="input-xlarge" path="lastName" id="lastName" value=""/>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="lastName">Role:</label>
                                <div class="controls">
                                    <spring:select cssClass="input-xlarge" path="roleBean.id" id="roleId" items="${roles}"/>
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








