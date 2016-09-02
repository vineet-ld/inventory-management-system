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
                        <a data-rel="tooltip" title="6 new members." class="well span3 top-block" href="#">
                            <span class="icon32 icon-red icon-help"></span>
                            <div>Orders (Requests)</div>
                            <div>507</div>
                            <span class="notification">6</span>
                        </a>

                        <a data-rel="tooltip" title="4 new pro members." class="well span3 top-block" href="#">
                            <span class="icon32 icon-color icon-check"></span>
                            <div>Orders (Completed)</div>
                            <div>228</div>
                            <span class="notification green">4</span>
                        </a>

                        <a data-rel="tooltip" title="$34 new sales." class="well span3 top-block" href="#">
                            <span class="icon32 icon-color icon-cart"></span>
                            <div>Products</div>
                            <div>$13320</div>
                            <span class="notification yellow">$34</span>
                        </a>

                        <a data-rel="tooltip" title="12 new messages." class="well span3 top-block" href="#">
                            <span class="icon32 icon-color icon-envelope-closed"></span>
                            <div>Messages</div>
                            <div>25</div>
                            <span class="notification red">12</span>
                        </a>
                    </div>

                </div>

            </div>

            <hr/>

            <%@include file="../../../../jsp/include/footer.jsp" %>

        </div>

    </body>


</html>





