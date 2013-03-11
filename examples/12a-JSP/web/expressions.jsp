<html>
    <head>
        <title>JSP Expressions</title>
    </head>
    <body>
        <h2>JSP Expressions</h2>
        <ul>
            <li>Current time: <%= new java.util.Date()%></li>
            <li>Your hostname:<%= request.getRemoteHost()%></li>
            <li>Your session ID: <%= session.getId()%></li>
            <li>The <code>testParam</code> form parameter:
                <%= request.getParameter("testParam")%></li>
        </ul>
        <p/>
        <a href="index.html">index</a>
    </body>
</html>