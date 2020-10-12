<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Chat</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
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


<c:choose>
    <c:when test="${not empty displayWarningPopup}">
        <div class="message-alert">
            <span class="message-closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                ${displayWarningPopup}
            <c:remove var="displayWarningPopup"/>
        </div>
    </c:when>
</c:choose>

<!-- Download modal -->
<div class="modal fade" id="downloadModal" tabindex="-1" role="dialog" aria-labelledby="downloadModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="downloadModalLabel">Download Messages</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form method = "GET" action="servlet.BasicServlet" class = "download-chat-container">
                <div class="modal-body">
                    <input type="datetime-local" id="startTime" name="startTime" step="1">
                    <label for="startTime">Start Time</label><br>
                    <input type="datetime-local" id="endTime" name="endTime" step="1">
                    <label for="endTime">End Time</label><br>
                    <input type="checkbox" id="format" name="format" value="xml">
                    <label for="format">Download in XML format (leave blank for plaintext)</label><br><br>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Download messages</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Clear chat modal -->
<div class="modal fade" id="clearChatModal" tabindex="-1" role="dialog" aria-labelledby="clearChatModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="clearChatModalLabel">Clear messages</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form method = "POST" action="servlet.ClearServlet" class = "clear-chat-container">
                <div class="modal-body">
                    <label for="dateFrom">Date from:</label>
                    <input type="datetime-local" id="dateFrom" name="dateFrom" step="1"><br>
                    <label for="dateTo">Date to:</label>
                    <input type="datetime-local" id="dateTo" name="dateTo" step="1"><br>
                    <label for="clearAll"> Clear all messages</label>
                    <input type="checkbox" id="clearAll" name="clearAll" value="clearAll"><br><br>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Clear messages</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>