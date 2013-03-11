<html>
    <head>
        <title>Hello World</title>
    </head>
    <body>

        <h1>This is a simple date that is generated in the server side:
            <span style="color: red"><%= new java.util.Date() %></span></h1>
         
        <% int count = Integer.parseInt(request.getParameter("number"));
        
        for (int i=0 ; i < count ; i++) { %>
            <h1 style="color: pink"><%= i %> Hello World!</h1>
        <% } %>
        
        <p/>
        <a href="index.html">index</a>
    </body>
</html>
