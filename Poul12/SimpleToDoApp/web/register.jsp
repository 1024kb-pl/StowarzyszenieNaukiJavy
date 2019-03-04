<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/fragments/menu.jspf"/>

<div class="container">

    <div class="row">

        <div class="col-sm-4">
        </div>

        <div class="col-sm-4" style="background-color:lavenderblush;">

            <form action="register" method="post">
                <h2>Register</h2>
                <div class="form-group">
                    <label for="InputUsername">Username</label>
                    <input type="text" class="form-control" id="InputUsername" placeholder="Enter username" name="username">
                </div>
                <div class="form-group">
                    <label for="InputEmail">Email address</label>
                    <input type="email" class="form-control" id="InputEmail" placeholder="Enter email" name="email">
                </div>
                <div class="form-group">
                    <label for="InputPassword">Password</label>
                    <input type="password" class="form-control" id="InputPassword" placeholder="Password" name="password">
                </div>
                <div class="form-group">
                    <label for="InputRepeatedPassword">Repeat password</label>
                    <input type="password" class="form-control" id="InputRepeatedPassword" placeholder="Repeat password" name="repeated-pass">
                </div>
                <button type="submit" class="btn btn-primary">Register</button>
            </form>

        </div>

        <div class="col-sm-4">
        </div>


    </div>
</div>


</body>
</html>
