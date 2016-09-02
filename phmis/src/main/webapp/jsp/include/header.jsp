<%-- 
    Document   : header
    Created on : Feb 17, 2015, 7:23:16 PM
    Author     : Vineet
--%>


<div class="navbar">
    <div class="navbar-inner">
        <div class="container-fluid">
            <div class="container-fluid">
                <a class="brand" href="#"> <img alt="Charisma Logo" src="${pageContext.request.contextPath}/img/logo.jpeg" /> <span>PHMIS</span></a>

                <div class="btn-group pull-right" >
                    <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="icon-user"></i><span class="hidden-phone" id="user-name"> ${sessionScope.user.firstName}</span>
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Profile</a></li>
                        <li class="divider"></li>
                        <li><a href="${pageContext.request.contextPath}/logout.htm">Logout</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<noscript>
<div class="alert alert-block span10">
    <h4 class="alert-heading">Warning!</h4>
    <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
</div>
</noscript>