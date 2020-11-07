<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <title>BoardContent</title>
</head>
<body>

<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <form class="form-inline my-2 my-lg-0" method="get" action="servlet.BasicServlet">
                <button class="btn btn-lg btn-outline-light" type="Submit">Home</button>
            </form>
            <li class="nav-item active">
                <a class="nav-link" href="createPost.jsp">Create Post<span class="sr-only">(current)</span></a>
            </li>
            <form class="form-inline my-2 my-lg-0" method="get" action="servlet.PaginationServlet">
                <button class="btn btn-sm btn-outline-primary" type="Submit">View Recent</button>
            </form>
        </ul>
        <form class="form-inline my-2 my-lg-0" method="post" action="servlet.SignoutServlet">
            <button class="btn btn-outline-success my-2 my-sm-0" type="Submit">Sign out</button>
        </form>
    </div>
</nav>

<c:choose>
    <c:when test="${not empty messageBoard}">
        <jsp:useBean id="messageBoard" type="java.util.concurrent.ConcurrentSkipListMap<java.lang.Long, models.Post>" scope="session"/>
        <div style="margin-top: 60px; margin-left:40%">
            <c:forEach items="${messageBoard.values()}" var="post">
                <div class="card" style="width: 25rem;" >
                    <c:if test = "${post.containsAttachment}">
                        <img src="data:image/jpg;base64,${post.attachment}" class="card-img-top" alt="No attachment">
                    </c:if>
                    <div class="card-body">
                        <div class="row">
                            <div class="column" style="float:left; width:50%;">
                                <h5 class="card-title">${post.postTitle}</h5>
                                <c:if test = "${post.timestamp ne post.lastModifiedTimestamp}">
                                    <h6 class="card-subtitle mb-2 text-muted">Updated</h6>
                                </c:if>
                                <p class="card-text">${post.message}</p>
                            </div>
                            <div class="column" style="float:right">
                                <form class="form-inline my-2 my-lg-0" action="servlet.UpdateServlet" method="get">
                                    <input type="hidden" name="postID" value=${post.postID}>
                                    <button class="btn btn-primary my-2 my-sm-0" type="Submit">Edit</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <c:if test = "${post.containsAttachment}">
                        <div class="dropdown">
                            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Attachment
                            </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                <form class="dropdown-item" method="get" action="servlet.DownloadServlet">
                                    <input type="hidden" name="postID" value=${post.postID}>
                                    <button type="submit" class="btn btn-primary">Download</button>
                                </form>
                                <form class="dropdown-item" method="post" action="servlet.AttachmentServlet">
                                    <input type="hidden" name="postID" value=${post.postID}>
                                    <button type="submit" class="btn btn-primary">Remove</button>
                                </form>
                            </div>
                        </div>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </c:when>
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

<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>
</html>
