<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Main Page</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
  <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
  <script src="js/bootstrap.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/fragments/menu.jspf"/>

<div class="container">
  <h3>Simple ToDo App</h3>
  <p>You can store tasks on this website.
  <p>Create new task, display it, and manage. Check for done, delete old task. </p>
</div>

<jsp:include page="/WEB-INF/fragments/footer.jspf"/>

</body>
</html>
