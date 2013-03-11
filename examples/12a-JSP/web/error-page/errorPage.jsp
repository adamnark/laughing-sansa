<html>
  <head>
      <title>Connection Error</title>
  </head>
  <body>
      <%@ page import="java.io.*" %>
      <%@ page isErrorPage="true" %>
     <h1>Oops. There was an error when you accessed the
          database.</h1>
    <h2>Here is the stack trace:</h2>
    <pre style="color:red">
        <% exception.printStackTrace(new PrintWriter(out)); %>
    </pre>
    <p/>
        <a href="../index.html">index</a>
  </body>
</html>
