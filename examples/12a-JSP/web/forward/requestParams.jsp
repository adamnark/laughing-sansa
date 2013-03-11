<html>
    <head><title>Print Request Params</title></head>
    <body>
        <%@ page import="java.util.*" %>
        <% Enumeration parameterNames = request.getParameterNames();%>
        <% while (parameterNames.hasMoreElements()) {%>
        <% 		String name = (String) parameterNames.nextElement();%>
        <h2><%= name%> : <%= request.getParameter(name)%> </h2>
        <% }%>
    </body>
    <p/>
    <a href="../index.html">index</a>
</html>
