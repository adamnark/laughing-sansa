<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Bean Example</title>
    </head>
    <body>
        <jsp:useBean id="accessCounter" class="examples.counter.CounterBean" scope="application"/>
        <% accessCounter.increment();%>
        <h1>Welcome to Page A</h1>
        <h2>Accesses to this application:
            <jsp:getProperty name="accessCounter" property="counter"/>
        </h2>
        <a href="pageB.jsp">Page B</a></body>
</html>