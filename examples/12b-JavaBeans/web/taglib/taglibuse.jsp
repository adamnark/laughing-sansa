<%--
    Document   : taglibuse
    Created on : Mar 4, 2012, 3:45:34 PM
    Author     : blecherl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="mytag" uri="/WEB-INF/tags/my_taglib.tld" %>
<html>
    <head>
        <title>Custom TagLib</title>
    </head>
    <body>
        <h1>Hello. The time is: <mytag:date/></h1>
    </body>
</html>

