<%--
    Document   : form
    Created on : Jan 17, 2012, 12:16:10 PM
    Author     : blecherl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head></head>
    <body>
        <p>Commons File Upload Example</p>
        <form action="upload" enctype="multipart/form-data" method="POST">
            <input type="file" name="file1"><br>
            <input type="Submit" value="Upload File"><br>
        </form>
    </body>
</html>