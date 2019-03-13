
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Message</title>
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

        <div class="col-md-4 col-md-offset-4">
            <h3>${requestScope.message}</h3>
        </div>

    </div>

</div>

<jsp:include page="/WEB-INF/fragments/footer.jspf"/>

</body>
</html>