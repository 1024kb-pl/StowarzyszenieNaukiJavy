<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit user</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/styles.css" type="text/css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/fragments/menu.jspf"/>

<c:if test="${not empty sessionScope.username}">

    <div class="container">

        <div class="row">

            <div class="col-sm-6 col-md-4 col-md-offset-4">

                <form action="editUser" method="post">
                    <h2>Change user data</h2>
                    <div class="form-group">
                        <label for="InputUsername">Username</label>
                        <input type="text" class="form-control" id="InputUsername" placeholder="Enter username" name="username" value="${requestScope.username}" required>
                    </div>
                    <div class="form-group">
                        <label for="InputEmail">Email address</label>
                        <input type="email" class="form-control" id="InputEmail" placeholder="Enter email" name="email" value="${requestScope.email}" required>
                    </div>
                    <div class="form-group">
                        <label for="InputPassword">Password</label>
                        <input type="password" class="form-control" id="InputPassword" placeholder="Password" name="password" required>
                    </div>
                    <div class="form-group">
                        <label for="InputRepeatedPassword">Repeat password</label>
                        <input type="password" class="form-control" id="InputRepeatedPassword" placeholder="Repeat password" name="repeated-pass" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Save</button>
                </form>

            </div>

        </div>
    </div>

</c:if>

<jsp:include page="/WEB-INF/fragments/footer.jspf"/>

</body>
</html>