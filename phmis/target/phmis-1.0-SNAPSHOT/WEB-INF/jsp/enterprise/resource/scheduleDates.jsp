<%-- 
    Document   : scheduleDates
    Created on : Apr 24, 2015, 10:39:16 AM
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

                                    <spring:form action="${pageContext.request.contextPath}/surgery/schedule.htm" commandName="request" method="POST" cssClass="form-horizontal">

                                        <spring:hidden path="id"/>
                                        
                                        <div class="control-group">
                                            <label class="control-label" for="date">Select Date</label>
                                            <div class="controls">
                                                <spring:select id="date" path="surgeryBean.scheduleDate" items="${schedulingDates}"/>
                                            </div>
                                        </div>

                                        <div class="control-group">
                                            <label class="control-label" for="room">Operating Room</label>
                                            <div class="controls">
                                                <spring:select id="room" path="surgeryBean.operatingRoomBean.id" items="${operatingRooms}"/>
                                            </div>
                                        </div>

                                        <div class="control-group">
                                            <label class="control-label" for="staff">Medical Staff</label>
                                            <div class="controls">
                                                <select id="staff" multiple data-rel="chosen" name="staff">
                                                    <c:forEach var="staff" items="${staffList}">
                                                        <option value="${staff.id}">${staff.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="control-group">
                                            <label class="control-label" for="item">Devices</label>
                                            <div class="controls">
                                                <select id="item" multiple data-rel="chosen" name="items">
                                                    <c:forEach var="item" items="${itemList}">
                                                        <option value="${item.id}">${item.productBean.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-actions">
                                            <input type="submit" class="btn btn-primary" value="Schedule">
                                            <a href="${pageContext.request.contextPath}/home.htm" class="btn">Back</a>
                                        </div>

                                    </spring:form>

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






