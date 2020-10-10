<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Chat</title>
    <link rel="stylesheet" href="index.css">
</head>
<body>
<form method = "POST" action="servlet.TemplateServlet" name="switchTemplate"></form>
<form method="GET" action="servlet.ClearServlet" name="refresh-page"></form>

<script type="text/javascript">
    function switchTemplateOnToggle() {document.forms["switchTemplate"].submit();}
    setInterval(function() {document.forms["refresh-page"].submit();}, 15000);
</script>

<label class="switch">
    <input type="checkbox" id="myCheckbox" onchange="switchTemplateOnToggle()">
    <span class="slider round"></span>
</label>

<c:choose>
    <c:when test="${not empty template}">
        <jsp:include page="template_one.jsp" />
    </c:when>
    <c:otherwise>
        <jsp:include page="template_two.jsp" />
    </c:otherwise>
</c:choose>

</body>
</html>