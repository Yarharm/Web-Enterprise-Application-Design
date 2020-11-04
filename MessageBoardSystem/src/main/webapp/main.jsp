<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${sessionScope.userID == null}">
    <c:redirect url="index.jsp"/>
</c:if>
<p>main page</p>
</body>
</html>
