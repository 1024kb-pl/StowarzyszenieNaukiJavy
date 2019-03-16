<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create new task</title>
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

            <form action="addTask" method="post">
                <h2>Add new task</h2>
                <div class="form-group">
                    <label for="InputTitle">Title</label>
                    <input type="text" class="form-control" id="InputTitle" placeholder="Enter title" name="title" required>
                </div>
                <div class="form-group">
                    <label for="InputDate">Date</label>
                    <input type="date" class="form-control" id="InputDate" name="taskdate" value="<c:out value="${LocalDate.now()}"/>">
                </div>
                <div class="form-group">
                    <label for="InputDescription">Description</label>
                    <textarea class="form-control" rows="5" id="InputDescription" name="description" wrap="hard" maxlength ="150" placeholder="Description task... (max 150 signs)"></textarea>
                </div>
                <input type="hidden" name="checktask" value="false">
                <button type="submit" class="btn btn-primary">Add</button>
            </form>

        </div>

    </div>
</div>

<jsp:include page="/WEB-INF/fragments/footer.jspf"/>

</body>
</html>