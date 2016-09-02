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
                        
                        <a data-rel="tooltip" title="Requests to Schedule Surgeries" class="well span4 top-block" href="#">
                            <span class="icon32 icon-red icon-pin"></span>
                            <div>Surgery Request</div>
                            <div>${totalCount}</div>
                            <!--<span class="notification"></span>-->
                        </a>

                        <a data-rel="tooltip" title="Your New Device Requests" class="well span4 top-block" href="#">
                            <span class="icon32 icon-color icon-clipboard"></span>
                            <div>Device Request</div>
                            <div>&nbsp;</div>
                            <!--<span class="notification green">4</span>-->
                        </a>

                        <a data-rel="tooltip" title="Manage Operating Rooms and Medical Staff" class="well span4 top-block" href="${pageContext.request.contextPath}/resources.htm">
                            <span class="icon32 icon-color icon-link"></span>
                            <div>Resources</div>
                            <div>&nbsp;</div>
                            <!--<span class="notification yellow"></span>-->
                        </a>
                            
                    </div>

                </div>

            </div>

            <hr/>

            <%@include file="../../../../jsp/include/footer.jsp" %>

        </div>

    </body>


</html>





