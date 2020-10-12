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

<div class="download-chat">
    <form method = "GET" action="servlet.BasicServlet" class = "download-chat-container">
        <input type="datetime-local" id="startTime" name="startTime" step="1">
        <label for="startTime">Start Time</label><br>
        <input type="datetime-local" id="endTime" name="endTime" step="1">
        <label for="endTime">End Time</label><br>
        <input type="checkbox" id="format" name="format" value="xml">
        <label for="format">Download in XML format (leave blank for plaintext)</label><br><br>

        <button type="submit" class="btn button">Download Messages</button>
    </form>
</div>

<div class="clear-chat">
    <form method="POST" action="servlet.ClearServlet" class="clear-chat-container">

        <input type="datetime-local" id="dateFrom" name="dateFrom" step="1">
        <label for="dateFrom">Date from:</label><br>


        <input type="datetime-local" id="dateTo" name="dateTo" step="1">
        <label for="dateTo">Date to:</label><br>


        <input type="checkbox" id="clearAll" name="clearAll" value="clearAll">
        <label for="clearAll"> Clear all messages</label><br><br>
        <input type="submit" value="Clear" class = "button">
    </form>
</div>
</section>

<c:choose>
    <c:when test="${not empty displayWarningPopup}">
        <div class="empty-message-alert">
            <span class="empty-message-closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                ${displayWarningPopup}
            <c:remove var="displayWarningPopup"/>
        </div>
    </c:when>
</c:choose>

</body>
</html>