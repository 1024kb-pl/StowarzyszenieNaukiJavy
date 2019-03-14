<%@ page import="java.time.LocalDate" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title>Task list</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="../../js/bootstrap.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/fragments/menu.jspf"/>

<div class="row">

    <div class="col-sm-3">

    </div>

    <div class="col-sm-4">

        <div class="form-group">
            <form class="form-inline" action="tasks" method="get">
                <label for="filters">Filters </label>
                <select class="form-control" id="filters" name="filter">
                    <option></option>
                    <option value="true">Checked</option>
                    <option value="false">Unchecked</option>
                    <option value="date">Date</option>
                </select>
                <input type="date" name="dateFilter" value="<c:out value="${LocalDate.now()}"/>">
                <button type="submit" class="btn btn-primary">Filter</button>
            </form>

        </div>

    </div>

    <div class="col-sm-4">

        <form class="form-inline" action="tasks" method="get">
            <label for="sort">Sort </label>
            <select class="form-control" id="sort" name="sort">
                <option></option>
                <option value="title">Title</option>
                <option value="date">Date</option>
                <option value="status">Checked</option>
            </select>
            <button type="submit" class="btn btn-primary">Sort</button>
        </form>

    </div>

    <div class="col-sm-3">

    </div>

</div>

<div class="container">

    <table class="table table-hover">
        <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Title</th>
            <th scope="col">Date</th>
            <th scope="col">Description</th>
            <th scope="col">Done</th>
            <th scope="col">Operation</th>
        </tr>
        </thead>

        <c:if test="${not empty requestScope.tasksList}">
            <c:set var="row" value="1"/>
            <c:forEach var="task" items="${requestScope.tasksList}">

                <tbody>
                <tr>
                    <th scope="row">${row + 1}</th>
                    <td>${task.title}</td>
                    <td>${task.date}</td>
                    <td>${task.description}</td>
                    <td>
                        <c:choose>
                            <c:when test="${task.taskDone == true}">
                                <span class="glyphicon glyphicon-ok"></span>
                            </c:when>
                            <c:otherwise>
                                <span class="glyphicon glyphicon-remove"></span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="getTaskInfo?id=${task.taskId}&title=${task.title}&date=${task.date}&description=${task.description}&done=${task.taskDone}"><button type="button" class="btn btn-primary">Edit</button></a>
                        <a href="deleteTask?id=${task.taskId}"><button type="button" class="btn btn-primary">Delete</button></a>
                    </td>
                </tr>
                </tbody>

            </c:forEach>
        </c:if>

    </table>

</div>

<jsp:include page="/WEB-INF/fragments/footer.jspf"/>

</body>
</html>
