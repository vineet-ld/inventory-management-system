<%-- 
    Document   : viewUser
    Created on : Apr 16, 2015, 5:36:40 AM
    Author     : Vineet
--%>

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
                                    <a href="javascript:window.history.back()">${enterprise.name}</a> <span class="divider">/</span>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/admin/hospitals/users/view.htm?uid=${user.id}">${user.firstName}&nbsp;${user.lastName}</a>
                                </li>
                            </c:if>
                            <c:if test='${enterprise.typeId == 2}'>
                                <li>
                                    <a href="${pageContext.request.contextPath}/admin/warehouses.htm">Warehouses</a> <span class="divider">/</span>
                                </li>
                                <li>
                                    <a href="javascript:history.back();">${enterprise.name}</a> <span class="divider">/</span>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/admin/warehouses/users/view.htm?uid=${user.id}">${user.firstName}&nbsp;${user.lastName}</a>
                                </li>
                            </c:if>
                            <c:if test='${enterprise.typeId == 3}'>
                                <li>
                                    <a href="${pageContext.request.contextPath}/admin/vendors.htm">Vendors</a> <span class="divider">/</span>
                                </li>
                                <li>
                                    <a href="javascript:history.back();">${enterprise.name}</a> <span class="divider">/</span>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/admin/vendors/users/view.htm?uid=${user.id}">${user.firstName}&nbsp;${user.lastName}</a>
                                </li>
                            </c:if>    
                        </ul>
                    </div>

                    <div class="row-fluid sortable">
                        <div class="box span12">
                            <div class="box-header well" data-original-title>
                                <h2><i class="icon-edit"></i> User Details</h2>
                            </div>
                            <div class="box-content">

                                <h4>${user.firstName}&nbsp;${user.lastName}</h4><br/>
                                <p><b>ID: </b>${user.number}</p>
                                <p><b>Email ID: </b>${user.emailId}</p>
                                <p><b>User Name: </b>${user.username}</p>
                                <p><b>Role: </b>${user.roleBean.name}</p>
                                <c:if test="${user.status == 1}">
                                    <p><b>Status: </b><span class="label label-success">Active</span></p>
                                </c:if>
                                <c:if test="${user.status == 0}">
                                    <p><b>Status: </b><span class="label label-warning">Inactive</span></p>
                                </c:if>
                                <%--<p><b>Address: </b></p>
                                <p>${enterprise.street}, 
                                    <c:if test='${!enterprise.suite.equals("")}'>
                                        ${enterprise.suite},
                                    </c:if>
                                    <br/>
                                    ${enterprise.city}, 
                                    ${enterprise.state} - 
                                    ${enterprise.zipCode}
                                </p><br/>
                                <p><b>Contact: </b>${enterprise.contact}</p>--%>
                                <p><b>Added On: </b>${user.creationDate}</p>
                                <c:if test='${user.modifiedDate != null}'>
                                    <p><b>Modified On: </b>${user.modifiedDate}</p>
                                </c:if>

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








