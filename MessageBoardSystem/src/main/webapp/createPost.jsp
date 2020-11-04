<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="post-chat-message">
    <form method="post" action="servlet.BasicServlet">
        <input type="text" name="title" id="title">
        <label for="title">Enter your title here</label>
        <br>
        <input type="text" name="message" id="message">
        <label for="message">Enter your message here</label>
        <br>
        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>
