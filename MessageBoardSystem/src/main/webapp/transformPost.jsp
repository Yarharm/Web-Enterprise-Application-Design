<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="xmlpost" type="java.lang.String" scope="session"/>
<jsp:useBean id="xslt" type="java.lang.String" scope="session"/>

<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <title>View Post XML</title>
</head>
<body>
<title>View Catalog (Transform View v1)</title>
<x:transform xml="${xmlpost}" xslt="${xslt}" />
<div><a class="btn btn-outline-info" href="main.jsp">Main page</a></div>
</body>
</html>
