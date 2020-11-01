<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>Send a message</h3>
    <div class="post-chat-message">
        <form method="get" action="servlet.BasicServlet">
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
