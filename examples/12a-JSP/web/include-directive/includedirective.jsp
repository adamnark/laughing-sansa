<html>
    <head>
        <title>Including JSP</title>
    </head>
    <body>
        <h2>Here is an interesting page.</h2>
        <p>Bla, Bla, Bla, Bla.</p>
        <%@ include file="accessCount.jsp" %>
        <jsp:include page="myMail.jsp"/>
    </body>
</html>
