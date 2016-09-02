<%-- 
    Document   : menu
    Created on : Mar 29, 2015, 9:24:14 PM
    Author     : Vineet
--%>

<div class="span2 main-menu-span">
    <div class="well nav-collapse sidebar-nav">
        <ul class="nav nav-tabs nav-stacked main-menu">
            <li><a class="ajax-link" href="${pageContext.request.contextPath}/search.htm"><i class="icon-search"></i><span class="hidden-tablet"> Search</span></a></li>
            <li><a class="ajax-link" href="${pageContext.request.contextPath}/messages.htm"><i class="icon-envelope"></i><span class="hidden-tablet"> Messages</span></a></li>
            <li><a class="ajax-link" href="${pageContext.request.contextPath}/contacts.htm"><i class="icon-book"></i><span class="hidden-tablet"> Contacts</span></a></li>
            <li><a class="ajax-link" href="changePassword.jsp"><i class="icon-lock"></i><span class="hidden-tablet"> Change Password</span></a></li>
            <li><a class="ajax-link" href="${pageContext.request.contextPath}/logout.htm"><i class="icon-off"></i><span class="hidden-tablet"> Logout</span></a></li>
        </ul>
    </div><!--/.well -->
</div>
