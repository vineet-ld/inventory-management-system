<%-- 
    Document   : activate
    Created on : Apr 14, 2015, 8:04:15 PM
    Author     : Vineet
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix = "spring" %>
<html lang="en">
    <head>
        <%@include file="../../../jsp/include/commonTags.jsp" %>
    </head>
    <body>

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
                            Please set your password for the account.
                        </div>
                        <spring:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/account/activate.htm?key=${key}" method="post" onsubmit="return validateActivation();" commandName="user">
                            <fieldset>

                                <div class="control-group">
                                    <label class="control-label" for="name">Enter Password:</label>
                                    <div class="controls">
                                        <spring:password cssClass="input-large span10" path="password" id="password"/>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label" for="name">Confirm Password:</label>
                                    <div class="controls">
                                        <input class="input-large span10" name="confirm" id="confirm" type="password" />
                                    </div>
                                </div>

                                <spring:hidden path="id"/>

                                <p class="center span5">
                                    <input type="submit" class="btn btn-primary" value="Save"/>
                                </p>

                                <div id="error-div" style="display: none">
                                    <div class="box-content alerts" id="popup">
                                        <div class="alert alert-error">
                                            <button type="button" class="close" data-dismiss="alert">×</button>
                                            <strong><span id="error"></span></strong>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                        </spring:form>>
                    </div><!--/span-->
                </div><!--/row-->
            </div><!--/fluid-row-->

        </div>
    </body>
</html>



