<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <title>Title</title>
</head>
<body>
<c:if test="${sessionScope.userID == null}">
    <c:redirect url="index.jsp"/>
</c:if>

<form method="post" action="servlet.UpdateServlet">

    <div class="form-group row">
        <label for="title" class="col-sm-2 col-form-label">Title</label>
        <div class="col-sm-10">
            <input name="title" type="text" class="form-control" id="title" placeholder="Title" value="${referredPost.getPostTitle()}" required>
        </div>
    </div>
    <div class="form-group row">
        <label for="message" class="col-sm-2 col-form-label">Message</label>
        <div class="col-sm-10">
            <textarea name="message" class="form-control" id="message" rows="3" placeholder="Message" required>${referredPost.getMessage()}</textarea>
        </div>
    </div>
    <div class="form-group">
        <label for="inputAttachment" class="col-sm-2 col-form-label">Attachment</label>
        <input name="attachment" type="file" class="form-control-file" id="inputAttachment">
    </div>
    <div class="form-group row">
        <div class="col-sm-10">
            <button type="submit" class="btn btn-primary">Update</button>
        </div>
    </div>

</form>

<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>
</html>
