<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create new task</title>
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

            <form action="addTask" method="post">
                <h2>Add new task</h2>
                <div class="form-group">
                    <label for="InputTitle">Title</label>
                    <input type="text" class="form-control" id="InputTitle" placeholder="Enter title" name="title">
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

        <div class="col-sm-4">
        </div>

    </div>
</div>

</body>
</html>