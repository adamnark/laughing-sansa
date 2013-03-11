<html>
    <head>
        <title>Include (action) Example</title>
    </head>

    <body>
        <h2>Included part begins:<h2><hr/>
                <jsp:include page="requestParams2.jsp" >
                    <jsp:param name="sessionID" value="<%= session.getId()%>" />
                    <jsp:param name="username" value="ZZZZZZZZ"/>
                </jsp:include>
                <hr/>
                <h2>Included part ends<h2>
                        <p/>
                        <a href="../index.html">index</a>
                        </body>
                        </html>