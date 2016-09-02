<%-- 
    Document   : resources
    Created on : Apr 22, 2015, 6:31:27 AM
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
                                <a href="${pageContext.request.contextPath}/home.htm">Home</a><span class="divider">/</span>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/resources.htm">Resources</a>
                            </li>
                        </ul>
                    </div>

                    <c:if test='${requestScope.roomAdded != null && requestScope.roomAdded == true}'>
                        <div>
                            <div class="box-content alerts" id="popup">
                                <div class="alert alert-success">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    <strong>New Operating Room has been added.</strong>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <c:if test='${requestScope.staffAdded != null && requestScope.staffAdded == true}'>
                        <div>
                            <div class="box-content alerts" id="popup">
                                <div class="alert alert-success">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    <strong>New Medical Staff has been added.</strong>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <div class="row-fluid sortable">
                        <div class="box span6">
                            <div class="box-header well" data-original-title>
                                <h2><i class="icon-edit"></i> Operating Rooms</h2>
                                <div class="box-icon">
                                    <a href="#" class="btn btn-setting btn-round" title="Add Operating Room" onclick="toggleDialog('room-div');"><i class="icon-plus"></i></a>
                                </div>
                            </div>
                            <div class="box-content">

                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Room ID</th>
                                            <th>Location</th>                                           
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <c:forEach var="room" items="${roomList}">
                                            <tr>
                                                <td>${room.number}</td>                                      
                                                <td>${room.location}</td>                                      
                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                </table>

                            </div>
                        </div>

                        <div class="box span6">
                            <div class="box-header well" data-original-title>
                                <h2><i class="icon-edit"></i> Medical Staff</h2>
                                <div class="box-icon">
                                    <a href="#" class="btn btn-setting btn-round" title="Add Medical Staff" onclick="toggleDialog('staff-div');"><i class="icon-plus"></i></a>
                                </div>
                            </div>
                            <div class="box-content">

                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Staff ID</th>
                                            <th>Name</th>                                           
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <c:forEach var="staff" items="${staffList}">
                                            <tr>
                                                <td>${staff.number}</td>                                      
                                                <td>${staff.name}</td>                                       
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

                <div id="room-div" style="display: none">
                    <spring:form action="${pageContext.request.contextPath}/resources/add.htm?type=room" commandName="operatingRoom" method="POST" cssClass="form-horizontal" onsubmit="return validateRoom();">

                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">×</button>
                            <h3>Add New Operating Room</h3>
                        </div>

                        <div class="modal-body">

                            <div id="error-div-room" style="display: none">
                                <div class="box-content alerts" id="popup">
                                    <div class="alert alert-error">
                                        <strong><span id="error-room"></span></strong>
                                    </div>
                                </div>
                            </div>

                            <fieldset>

                                <div class="control-group">
                                    <label class="control-label" for="location">Location:</label>
                                    <div class="controls">
                                        <spring:input  cssClass="input-xlarge" path="location" id="location"/>
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

                <div id="staff-div" style="display: none">
                    <spring:form action="${pageContext.request.contextPath}/resources/add.htm?type=staff" commandName="medicalStaff" method="POST" cssClass="form-horizontal" onsubmit="return validateStaff();">

                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">×</button>
                            <h3>Add New Medical Staff</h3>
                        </div>

                        <div class="modal-body">

                            <div id="error-div-staff" style="display: none">
                                <div class="box-content alerts" id="popup">
                                    <div class="alert alert-error">
                                        <strong><span id="error-staff"></span></strong>
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

                            </fieldset>

                        </div> 

                        <div class="modal-footer">
                            <input type="submit" class="btn btn-primary" value="Add"/>
                            <input type="reset" class="btn" data-dismiss="modal" value="Cancel"/>
                        </div>

                    </spring:form>
                </div>
            </div>

            <%@include file="../../../../jsp/include/footer.jsp" %>

        </div>

    </body>


</html>







