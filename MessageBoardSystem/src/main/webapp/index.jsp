<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>MessageBoardSystem</title>
    <link rel="stylesheet" href="index.css">
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.userID != null}">
        <jsp:include page="main.jsp" />
    </c:when>
    <c:otherwise>
        <jsp:include page="login.jsp" />
    </c:otherwise>
</c:choose>
</body>
</html>
