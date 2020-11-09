<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->

    <title>Title</title>
</head>
<body>

<c:if test="${empty userID}">
    <c:redirect url="index.jsp"/>
</c:if>

<div id="change-password">
    <h3 class="text-center text-white pt-5">change password form</h3>
    <div class="container">
        <div id="change-password-row" class="row justify-content-center align-items-center">
            <div id="change-password-column" class="col-md-6">
                <div id="change-password-box" class="col-md-12">
                    <form id="change-password-form" class="form" action="" method="get">
                        <h3 class="text-center text-info">Change Password</h3>
                        <div class="form-group">
                            <label for="currentPassword" class="text-info">Enter your current password:</label><br>
                            <input type="text" name="currentPassword" id="currentPassword" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="newPassword" class="text-info">Enter your new password:</label><br>
                            <input type="text" name="newPassword" id="newPassword" class="form-control">
                        </div>
                        <div class="form-group">
                            <input type="submit" name="submit" class="btn btn-info btn-md" value="submit" onclick="alert('this function has not yet been implemented')">
                        </div>
                    </form>
                    <div id="register-link" class="text-left">
                        <a href="main.jsp" class="text-info"><button class="btn btn-info btn-md">Cancel</button></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
<style>
    .message-alert {
        top:2%;
        left:42%;
        padding: 20px;
        background-color: #f44336;
        color: white;
        position: absolute;
        bottom: 8px;
        max-height: 25px;
    }

    .message-closebtn {
        margin-left: 15px;
        color: white;
        font-weight: bold;
        float: right;
        font-size: 22px;
        line-height: 20px;
        cursor: pointer;
        transition: 0.3s;
    }
</style>
<c:choose>
    <c:when test="${not empty displayWarningPopup}">
        <div class="message-alert">
            <span class="message-closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                ${displayWarningPopup}
            <c:remove var="displayWarningPopup"/>
        </div>
    </c:when>
</c:choose>

</body>
</html>