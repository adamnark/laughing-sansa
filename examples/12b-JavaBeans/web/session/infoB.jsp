<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="userInfo" class="examples.session.UserInfoBean" scope="session"/>
<jsp:setProperty name="userInfo" property="*"/>

<html>
    <head>
        <title>Page B</title>
    </head>
    <body>
        <h1>Hello
            <jsp:getProperty name="userInfo" property="firstName"/>
            <jsp:getProperty name="userInfo" property="lastName"/>,
        </h1>
        <h1>Have a nice session!</h1>
        <h2>
            <a href="infoA.jsp">User Info A</a>
        </h2>
    </body>
</html>