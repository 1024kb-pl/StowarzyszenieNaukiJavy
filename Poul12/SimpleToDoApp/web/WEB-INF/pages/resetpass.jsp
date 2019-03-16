<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reset Password</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/styles.css" type="text/css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/fragments/menu.jspf"/>

<div class="container">

    <div class="row">

        <div class="col-sm-6 col-md-4 col-md-offset-4">

            <form action="reset" method="post">
                <h2>Reset password</h2>
                <div class="form-group">
                    <label for="InputEmail">Email</label>
                    <input type="email" class="form-control" id="InputEmail" placeholder="Enter email" name="email" required>
                </div>
                <button type="submit" class="btn btn-primary">Reset</button>
            </form>

        </div>

    </div>
</div>

<jsp:include page="/WEB-INF/fragments/footer.jspf"/>

</body>
</html>
