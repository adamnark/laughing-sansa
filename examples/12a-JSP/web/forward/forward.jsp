<%! int even = 0; %>
<% even = (1 - even); %>
<h1>Before Forward</h1>
<% if (even == 0) { %>
	<jsp:forward page="requestParams.jsp" >
		<jsp:param name="sessionID" value="<%= session.getId() %>" />
		<jsp:param name="even" value="true" />
	</jsp:forward>
<% } else { %>
	<jsp:forward page="requestParams.jsp" >
		<jsp:param name="sessionID" value="<%= session.getId() %>" />
		<jsp:param name="even" value="false" />
	</jsp:forward>
<% } %>