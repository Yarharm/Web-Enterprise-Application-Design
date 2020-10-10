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
    <link rel="stylesheet" href="index.css">
    <link rel="stylesheet" id="template">
</head>
<body>
<form method="GET" action="servlet.ClearServlet" name="refresh-page"></form>

<label class="switch">
    <input type="checkbox" id="myCheckbox" onchange="switchTemplateOnToggle()">
    <span class="slider round"></span>
</label>

<script type="text/javascript">
    const templateStatus = localStorage.getItem('templateStatus');
    const template = document.getElementById('template');
    const checkbox = document.getElementById('myCheckbox');
    if (templateStatus === 'template_one'){
        template.setAttribute('href', 'template_one.css');
        localStorage.setItem('templateStatus', 'template_one');
        checkbox.checked = true;
    } else {
        template.setAttribute('href', 'template_two.css');
        localStorage.setItem('templateStatus', 'template_two');
        checkbox.checked = false;
    }

    function switchTemplateOnToggle() {
        const templateStatus = localStorage.getItem('templateStatus');
        const template = document.getElementById('template');
        if(templateStatus === 'template_one') {
            template.setAttribute('href', 'template_two.css');
            localStorage.setItem('templateStatus', 'template_two');
        } else {
            template.setAttribute('href', 'template_one.css');
            localStorage.setItem('templateStatus', 'template_one');
        }
        console.log(localStorage.getItem('template'));
    }

    setInterval(function() {document.forms["refresh-page"].submit();}, 15000);
    $(document).ready(function(){
        $('#action_menu_btn').click(function(){
            $('.action_menu').toggle();
        });
    });
</script>

<div class="template_two_toolbar">
    <nav class="navbar fixed-top navbar-expand navbar navbar-dark bg-dark">
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
</div>

<div class="template_one_chat container-fluid h-100">
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
                                <div class=" d-flex justify-content-start mb-4">
                                    <div class="msg_cotainer_send">${chatMessage.user}
                                    </div>
                                    <div class="msg_cotainer">${chatMessage.message}
                                        <span class="msg_time">${chatMessage.timestamp}</span>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </div>
                <div class="card-footer">
                    <form method="POST" action="servlet.BasicServlet" class="input-group">
                        <div>
                            <input type="text" class="form-control type_msg" placeholder="Enter username" name="userName">
                        </div>
                        <textarea name="message" class="form-control type_msg" placeholder="Type your message..." required></textarea>
                        <div class="input-group-append">
                            <button type="submit" class="input-group-text send_btn"><i class="fas fa-location-arrow"></i></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="template_two_chat">
    <c:choose>
        <c:when test="${not empty chatWindow}">
            <div class="chat-2">
                <c:forEach items="${chatWindow}" var="chatMessage">
                    <div class="card text-white rounded border-dark bg-primary mb-0 shadow">
                        <div class="card-body">
                            <h5 class="card-title">${chatMessage.user}</h5>
                            <blockquote class="blockquote mb-0">
                                <p class="text-break">${chatMessage.message}</p>
                                <footer class="blockquote-footer text-white"> <cite title="Source Title">${chatMessage.timestamp}</cite></footer>
                            </blockquote>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
    </c:choose>

    <section class="aside">
        <div class="send-message border-dark bg-info">
            <form method="POST" action="servlet.BasicServlet" class="send-message-container bg-info">
                <textarea class="text-white bg-info" placeholder="Your message.." name="message" required></textarea>
                <input type="text" id="username" class="user text-white bg-info" placeholder="Enter username" name="userName">
                <button type="submit" class="btn">Send</button>
            </form>
        </div>
    </section>
</div>

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