<%-- 
    Document   : changePassword
    Created on : Apr 1, 2015, 8:18:59 PM
    Author     : Vineet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="../../commonTags.jsp" %>
    </head>
    <body>

        <%@include file="../../header.jsp" %>

        <div class="container-fluid">
            <div class="row-fluid">

                <%@include file="../../menu.jsp" %>

                <div id="content" class="span10">

                    <c:if test='${requestScope.message != null}'>
                        <div>
                            <div class="box-content alerts" id="popup">
                                <c:if test='${requestScope.message.equals("Password updated")}'>
                                    <div class="alert alert-success">
                                        <button type="button" class="close" data-dismiss="alert">×</button>
                                        <strong>${requestScope.message}</strong>
                                    </div>
                                </c:if>
                                <c:if test='${requestScope.message.equals("Invalid Password")}'>
                                    <div class="alert alert-error">
                                        <button type="button" class="close" data-dismiss="alert">×</button>
                                        <strong>${requestScope.message}</strong>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </c:if>

                    <div class="row-fluid sortable">

                        <div class="box span12">
                            <div class="box-header well" data-original-title>
                                <h2><i class="icon-book"></i> Your Contacts</h2>
                            </div>
                            <div class="box-content">

                                <form class="form-horizontal" action="changePassword.htm" method="post" onsubmit="return validatePassword()">
                                    <fieldset>

                                        <div class="control-group">
                                            <label class="control-label" for="oldPassword">Old Password:</label>
                                            <div class="controls">
                                                <input class="input-xlarge" name="oldPassword" id="oldPassword" type="password"/>
                                            </div>
                                        </div>

                                        <div class="control-group">
                                            <label class="control-label" for="newPassword">New Password:</label>
                                            <div class="controls">
                                                <input class="input-xlarge" name="newPassword" id="newPassword" type="password"/>
                                            </div>
                                        </div>

                                        <div class="control-group">
                                            <label class="control-label" for="reTypePassword">Retype Password:</label>
                                            <div class="controls">
                                                <input class="input-xlarge" id="reTypePassword" type="password"/>
                                            </div>
                                        </div>

                                        <div class="form-actions">
                                            <button type="submit" class="btn btn-primary">Change</button>
                                        </div>

                                    </fieldset>
                                </form>

                            </div>
                        </div>

                    </div>

                </div>

            </div>
        </div>

    </body>


</html>



