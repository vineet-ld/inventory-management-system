<%-- 
    Document   : login
    Created on : Apr 2, 2015, 7:28:03 PM
    Author     : Vineet
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<html lang="en">
    <head>
        <%@include file="../../../jsp/include/commonTags.jsp" %>
    </head>
    <body>

        <div class="container-fluid">
            <div class="row-fluid">

                <div class="row-fluid">
                    <div class="span12 center login-header">
                        <h2>ADMINISTRATION SECURE LOGIN</h2>
                    </div><!--/span-->
                </div><!--/row-->

                <div class="row-fluid">
                    <div class="well span5 center login-box">
                        <div class="alert alert-info">
                            Please login with your Username and Password.
                        </div>

                        <form class="form-horizontal" action="${pageContext.request.contextPath}/admin/login.htm" method="POST" onsubmit="return validateLogin()">
                            <fieldset>
                                <div class="input-prepend" title="Username" data-rel="tooltip">
                                    <span class="add-on"><i class="icon-user"></i></span><input autofocus class="input-large span10" name="username" id="username" type="text"/>
                                </div>
                                <div class="clearfix"></div>

                                <div class="input-prepend" title="Password" data-rel="tooltip">
                                    <span class="add-on"><i class="icon-lock"></i></span><input class="input-large span10" name="password" id="password" type="password"/>
                                </div>
                                <div class="clearfix"></div>

                                <p class="center span5">
                                    <input type="submit" class="btn btn-primary" value="Login"/>
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
                        </form>
                    </div><!--/span-->
                </div><!--/row-->
            </div><!--/fluid-row-->

            <hr/>

            <%@include file="../../../jsp/include/footer.jsp" %>

        </div>
    </body>
</html>



