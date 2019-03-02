<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
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

        <div class="col-sm-4" style="background-color:lavender;">

        </div>

        <div class="col-sm-4" style="background-color:lavenderblush;">

            <form action="login" method="post">
                <h2>Login</h2>
                <div class="form-group">
                    <label for="InputUsername">Username</label>
                    <input type="text" class="form-control" id="InputUsername" placeholder="Enter username" name="username">
                </div>
                <div class="form-group">
                    <label for="InputPassword">Password</label>
                    <input type="password" class="form-control" id="InputPassword" placeholder="Password" name="password">
                </div>

                <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">Check me out</label>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>

        </div>

        <div class="col-sm-4" style="background-color:lavenderblush;"></div>

    </div>
</div>

</body>
</html>
