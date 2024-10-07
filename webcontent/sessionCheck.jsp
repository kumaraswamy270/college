<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	// Check if the user is logged in
	String username = (String) session.getAttribute("username");
	if (username == null) {
		// If the user is not logged in, redirect to the login page with sessionexpired=true
		response.sendRedirect("login.jsp?sessionexpired=true");
		return; // Stop further processing
	}
%>

<c:if test="${not empty param.sessionexpired}">
	<div id="sessionexpired" class="error">Your session has expired.
		Please Login again.</div>
</c:if>

