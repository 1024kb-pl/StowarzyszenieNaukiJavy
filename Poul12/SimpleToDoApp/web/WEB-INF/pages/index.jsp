<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Main Page</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="../../css/bootstrap.min.css" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="../../js/bootstrap.js"></script>
</head>
<body>

<jsp:include page="../fragments/menu.jspf"/>

<div class="container">
  <h3>Simple ToDo App</h3>
  <p>You can store tasks on this website.
  <p>Create new task, display it, and manage. Check for done, delete old task. </p>
</div>

<jsp:include page="../fragments/footer.jspf"/>

</body>
</html>
