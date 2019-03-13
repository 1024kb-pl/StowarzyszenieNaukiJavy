<%@ page import="pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.User" %>
<%@ page import="pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service.UserServiceImpl"%>
<%@ page import="java.util.Optional" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit user</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/fragments/menu.jspf"/>

<%
    UserServiceImpl userService = UserServiceImpl.getInstance();
    Optional<User> user = userService.getUserByUsername((String)request.getSession(false).getAttribute("username"));
            if(user.isPresent())
            {%>
<div class="container">

    <div class="row">

        <div class="col-md-4 col-md-offset-4" style="background-color:lavenderblush;">

            <form action="editUser" method="post">
                <h2>Change user data</h2>
                <div class="form-group">
                    <label for="InputUsername">Username</label>
                    <input type="text" class="form-control" id="InputUsername" placeholder="Enter username" name="username" value="<%=user.get().getUsername()%>">
                </div>
                <div class="form-group">
                    <label for="InputEmail">Email address</label>
                    <input type="email" class="form-control" id="InputEmail" placeholder="Enter email" name="email" value="<%=user.get().getEmail()%>">
                </div>
                <div class="form-group">
                    <label for="InputPassword">Password</label>
                    <input type="password" class="form-control" id="InputPassword" placeholder="Password" name="password">
                </div>
                <div class="form-group">
                    <label for="InputRepeatedPassword">Repeat password</label>
                    <input type="password" class="form-control" id="InputRepeatedPassword" placeholder="Repeat password" name="repeated-pass">
                </div>
                <button type="submit" class="btn btn-primary">Save</button>
            </form>

        </div>

    </div>
</div>

<%}else{%>

<h3>Nie znaleziono użytkownika</h3>

<%}%>

<jsp:include page="/WEB-INF/fragments/footer.jspf"/>

</body>
</html>