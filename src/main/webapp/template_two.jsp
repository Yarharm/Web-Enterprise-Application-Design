<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Chat</title>
    <link rel="stylesheet" href="template_two.css">
</head>
<body>
<nav class="navbar navbar-expand navbar navbar-dark bg-dark">
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <button type="button" class="nav-item" data-toggle="modal" data-target="#clearChatModal">
                Clear messages
            </button>
            <button type="button" class="nav-item" data-toggle="modal" data-target="#downloadModal">
                Download messages
            </button>
        </div>
    </div>
</nav>

<c:choose>
    <c:when test="${not empty chatWindow}">
        <div class="chat-2">
            <c:forEach items="${chatWindow}" var="chatMessage">
                <div class="chat-window-message">
                    <p class="user-name">${chatMessage.user}</p>
                    <p>${chatMessage.message}</p>
                    <span class="time-right">${chatMessage.timestamp}</span>
                </div>
            </c:forEach>
        </div>
    </c:when>
</c:choose>

<section class = "aside">
<div class="send-message">
    <form method="POST" action="servlet.BasicServlet" class="send-message-container">
        <textarea placeholder="Your message.." name="message" required></textarea>
        <input type="text" id="username" class="user" placeholder="Enter username" name="userName">
        <button type="submit" class="btn">Send</button>
    </form>
</div>
</section>

</body>
</html>