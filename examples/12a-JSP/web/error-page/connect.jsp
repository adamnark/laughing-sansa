<html>
    <head>
        <title>Reading From Database </title>
    </head>
    <body>
        <%@ page import="java.sql.*" %>
        <%@ page errorPage="errorPage.jsp" %>
        <%
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/homerDB",
                    "homer", "doughnuts");
        %>
        <h2>Can Connect!!</h2>
        <p/>
        <a href="../index.html">index</a>
    </body>
</html>