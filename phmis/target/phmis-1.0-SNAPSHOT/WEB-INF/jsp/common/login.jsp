<%-- 
    Document   : login.jsp
    Created on : Mar 29, 2015, 6:05:48 AM
    Author     : Vineet
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix = "spring" %>
<html lang="en">
    <head>
        <%@include file="../../../jsp/include/commonTags.jsp" %>
    </head>
    <body>

        <c:if test="${LoginFailed != null}">
            <script>alert("Invalid Username and/or Password");</script>
        </c:if>

        <div class="container-fluid">
            <div class="row-fluid">

                <div class="row-fluid">
                    <div class="span12 center login-header">
                        <h2>WELCOME TO CONTACT MANAGEMENT SYSTEM</h2>
                    </div><!--/span-->
                </div><!--/row-->

                <div class="row-fluid">
                    <div class="well span5 center login-box">
                        <div class="alert alert-info">
                            Please login with your Username and Password.
                        </div>
                        <spring:form class="form-horizontal" action="login.htm" method="post" onsubmit="return validateLogin();" commandName="user">
                            <fieldset>
                                <div class="input-prepend" title="Username" data-rel="tooltip">
                                    <span class="add-on"><i class="icon-user"></i></span><spring:input cssClass="input-large span10" path="username" id="username"/>
                                </div>
                                <div class="clearfix"></div>

                                <div class="input-prepend" title="Password" data-rel="tooltip">
                                    <span class="add-on"><i class="icon-lock"></i></span><spring:password cssClass="input-large span10" path="password" id="password"/>
                                </div>
                                <div class="clearfix"></div>

                                <div class="input-prepend">
                                    <label class="remember" for="remember"><input type="checkbox" name="remember" id="remember" value="1"/>Remember me</label>
                                </div>
                                <div class="clearfix"></div>

                                <p class="center span5">
                                    <input type="submit" class="btn btn-primary" value="Login"/>
                                </p>

                                <div id="error-div" style="display: none">
                                    <div class="box-content alerts" id="popup">
                                        <div class="alert alert-error">
                                            <strong><span id="error"></span></strong>
                                        </div>
                                    </div>
                                </div>

                                <c:if test="${auth == false}">
                                    <div id="error-div">
                                        <div class="box-content alerts" id="popup">
                                            <div class="alert alert-error">
                                                <strong><span id="error">User Name and/or Password Invalid</span></strong>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                            </fieldset>
                        </spring:form>
                    </div><!--/span-->
                </div><!--/row-->
            </div><!--/fluid-row-->

        </div>
    </body>
</html>


