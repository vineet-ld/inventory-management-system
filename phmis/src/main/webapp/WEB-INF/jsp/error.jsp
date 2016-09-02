<%-- 
    Document   : error
    Created on : Apr 7, 2015, 8:28:51 PM
    Author     : Vineet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="../../jsp/include/commonTags.jsp" %>
    </head>
    <body>

        <%@include file="../../jsp/include/header.jsp" %>

        <div class="container-fluid">
            <div class="row-fluid">

                <%--<%@include file="../../menu.jsp" %>--%>

                <div id="content" class="span10">

                    <div class="row-fluid sortable">

                        <div class="box span12">
<!--                            <div class="box-header well" data-original-title>
                                <h2><i class="icon-book"></i> Your Contacts</h2>
                            </div>-->
                            <div class="box-content">

                                <span>Some Error occured while processing the request.</span>

                            </div>
                        </div>

                    </div>

                </div>

            </div>
        </div>

    </body>


</html>




