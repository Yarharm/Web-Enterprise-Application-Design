<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Chat</title>
    <!------ Include the above in your HEAD tag ---------->
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.js"></script>
    <link rel="stylesheet" href="template_one.css">
</head>
<body>
<script>
    $(document).ready(function(){
        $('#action_menu_btn').click(function(){
            $('.action_menu').toggle();
        });
    });
</script>

<div class="container-fluid h-100">
    <div class="row justify-content-center h-100">
        <div class="col-md-8 col-xl-6 chat">
            <div class="card">
                <div class="card-header msg_head">
                    <div class="d-flex bd-highlight">
                        <div class="user_info">
                            <span>Chat window</span>
                        </div>
                    </div>
                    <span id="action_menu_btn"><i class="fas fa-ellipsis-v"></i></span>
                    <div class="action_menu">
                        <ul>
                            <li>
                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#clearChatModal">
                                    Clear messages
                                </button>
                            </li>
                            <li>
                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#downloadModal">
                                    Download messages
                                </button>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="card-body msg_card_body">
                    <c:choose>
                        <c:when test="${not empty chatWindow}">
                            <c:forEach items="${chatWindow}" var="chatMessage">
                                <div class="d-flex justify-content-start mb-4">
                                    <div class="msg_cotainer_send">${chatMessage.user}
                                        <span class="msg_time">${chatMessage.timestamp}</span>
                                    </div>
                                    <div class="msg_cotainer">${chatMessage.message}</div>
                                </div>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </div>
                <div class="card-footer">
                    <form method="POST" action="servlet.BasicServlet" class="input-group">
                        <textarea name="message" class="form-control type_msg" placeholder="Type your message..." required></textarea>
                        <div class="input-group-append">
                            <button type="submit" class="input-group-text send_btn"><i class="fas fa-location-arrow"></i></button>
                        </div>
                        <input type="text" class="form-control type_msg" placeholder="Enter username" name="userName">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<c:choose>
    <c:when test="${not empty displayWarningPopup}">
        <div class="empty-message-alert">
            <span class="empty-message-closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
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

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>