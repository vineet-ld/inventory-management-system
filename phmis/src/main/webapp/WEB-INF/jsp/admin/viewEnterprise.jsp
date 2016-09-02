<%-- 
    Document   : viewenterprise
    Created on : Apr 10, 2015, 4:05:48 AM
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
                            <c:if test='${enterprise.typeId == 1}'>
                                <li>
                                    <a href="${pageContext.request.contextPath}/admin/hospitals.htm">Hospitals</a> <span class="divider">/</span>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/admin/hospitals/view.htm?eid=${enterprise.id}">View</a>
                                </li>
                            </c:if>
                            <c:if test='${enterprise.typeId == 2}'>
                                <li>
                                    <a href="${pageContext.request.contextPath}/admin/warehouses.htm">Warehouses</a> <span class="divider">/</span>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/admin/warehouses/view.htm?eid=${enterprise.id}">View</a>
                                </li>
                            </c:if>
                            <c:if test='${enterprise.typeId == 3}'>
                                <li>
                                    <a href="${pageContext.request.contextPath}/admin/vendors.htm">Vendors</a> <span class="divider">/</span>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/admin/vendors/view.htm?eid=${enterprise.id}">View</a>
                                </li>
                            </c:if>    
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
                                <h2><i class="icon-edit"></i> Enterprise Details</h2>
                                <div class="box-icon">
                                    <a href="${pageContext.request.contextPath}/admin/hospitals/edit.htm?eid=${enterprise.id}" class="btn btn-round" title="Edit Details"><i class="icon-pencil"></i></a>
                                </div>
                            </div>
                            <div class="box-content">

                                <h4>${enterprise.name}</h4><br/>
                                <p><b>ID: </b>${enterprise.number}</p><br/>
                                <p><b>Address: </b></p>
                                <p>${enterprise.street}, 
                                    <c:if test='${!enterprise.suite.equals("")}'>
                                        ${enterprise.suite},
                                    </c:if>
                                    <br/>
                                    ${enterprise.city}, 
                                    ${enterprise.state} - 
                                    ${enterprise.zipCode}
                                </p><br/>
                                <p><b>Contact: </b>${enterprise.contact}</p>
                                <p><b>URL: </b><a target="_blank" href="${enterprise.url}">${enterprise.url}</a></p><br/>
                                <p><b>County: </b>${county}</p><br/>
                                <p><b>Added On: </b>${enterprise.creationDate}</p>
                                <c:if test='${enterprise.modifiedDate != null}'>
                                    <p><b>Modified On: </b>${enterprise.modifiedDate}</p>
                                </c:if>

                            </div>
                        </div>

                    </div>

                    <div class="row-fluid sortable">
                        <div class="box span12">
                            <div class="box-header well" data-original-title>
                                <h2><i class="icon-users "></i> Users</h2>
                                <div class="box-icon">
                                    <a href="#" class="btn btn-setting btn-round" title="Add User"><i class="icon-user"></i></a>
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
                                        <c:forEach var="user" items="${requestScope.enterprise.users}">
                                            <tr>
                                                <td><a href="${pageContext.request.contextPath}/admin/hospitals/users/view.htm?uid=${user.id}">${user.number}</a></td>                                      
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

                <spring:form action="${pageContext.request.contextPath}/admin/enterprise/user/add.htm?eid=${enterprise.id}&et=${enterprise.typeId}" commandName="user" method="POST" cssClass="form-horizontal" onsubmit="return validateUser();">

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
                                    <spring:input  cssClass="input-xlarge" path="username" id="username"/>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="emailId">Email ID:</label>
                                <div class="controls">
                                    <spring:input  cssClass="input-xlarge" path="emailId" id="emailId"/>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="firstName">First Name:</label>
                                <div class="controls">
                                    <spring:input cssClass="input-xlarge" path="firstName" id="firstName"/>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="lastName">Last Name:</label>
                                <div class="controls">
                                    <spring:input cssClass="input-xlarge" path="lastName" id="lastName"/>
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







