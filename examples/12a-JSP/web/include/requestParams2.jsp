<%@ page import="java.util.*" %>
<% Enumeration parameterNames = request.getParameterNames(); %>
<% while (parameterNames.hasMoreElements()) { %>
<% 		String name = (String)parameterNames.nextElement(); %>
<h2><%= name %> : <%= request.getParameter(name) %> </h2>
<% } %>