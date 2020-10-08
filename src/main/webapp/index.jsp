<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Chat</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

<c:choose>
    <c:when test="${empty chatWindow}"><p>zz</p></c:when>
    <c:otherwise>
        <div class="chat-window">
            <c:forEach items="${chatWindow}" var="chatMessage">
                <div class="chat-window-message">
                    <p class="user-name">${chatMessage.user}</p>
                    <p>${chatMessage.message}</p>
                    <span class="time-right">${chatMessage.timestamp}</span>
                </div>
            </c:forEach>
        </div>
    </c:otherwise>
</c:choose>


<div class="send-message">
    <form method="POST" action="BasicServlet" class="send-message-container">
        <textarea placeholder="Your message.." name="message" required></textarea>
        <input type="text" id="username" class="user" placeholder="Enter username" name="userName">
        <button type="submit" class="btn">Send</button>
    </form>

    <form method = "GET" action="BasicServlet" class = "send-message-container">
<<<<<<< 02e002ef19da7613ca0a48702fd3b7584c65acfd
<<<<<<< 69708ca62f3787fb529294f467ca469c028884a8
<<<<<<< 32082e31fd4a321eda069daf68dd748b08e65285
        <input type="datetime-local" id="startTime" name="startTime">
        <label for="startTime">Start Time</label>
        <p></p>
        <input type="datetime-local" id="endTime" name="endTime">
=======
        <input type="datetime-local" id="startTime" name="startTime" min="2019-01-01T00:00">
        <label for="startTime">Start Time</label>
        <p></p>
        <input type="datetime-local" id="endTime" name="endTime" min="2019-01-01T00:00">
>>>>>>> fixed commented issues
=======
        <input type="datetime-local" id="startTime" name="startTime">
        <label for="startTime">Start Time</label>
        <p></p>
        <input type="datetime-local" id="endTime" name="endTime">
>>>>>>> fixed more commented issues, added handling for start date being after end date
        <label for="endTime">End Time</label>
        <p></p>

        <input type="checkbox" id="format" name="format" value="xml">
        <label for="format">Download in XML format (leave blank for plaintext)</label>


<<<<<<< 69708ca62f3787fb529294f467ca469c028884a8
=======
>>>>>>> added doGet functionality
=======
>>>>>>> fixed commented issues
        <button type="submit" class="btn">Download Messages</button>
    </form>
</div>
</div>

</body>
</html>