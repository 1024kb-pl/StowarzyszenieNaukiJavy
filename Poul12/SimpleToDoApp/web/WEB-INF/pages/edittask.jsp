<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit task</title>
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

            <form action="editTask" method="post">
                <h2>Edit task</h2>
                <input type="hidden" name="task_id" value="<c:out value="${requestScope.id}"/>">
                <div class="form-group">
                    <label for="InputTitle">Title</label>
                    <input type="text" class="form-control" id="InputTitle" placeholder="Enter title" name="title" value="<c:out value="${requestScope.title}"/>">
                </div>
                <div class="form-group">
                    <label for="InputDate">Date</label>
                    <input type="date" class="form-control" id="InputDate" name="taskdate" value="<c:out value="${requestScope.date}"/>">
                </div>
                <div class="form-group">
                    <label for="InputDescription">Description</label>
                    <textarea class="form-control" rows="5" id="InputDescription" name="description" wrap="hard" maxlength ="150" placeholder="Description task... (max 150 signs)"><c:out value="${requestScope.description}"/></textarea>
                </div>
                <input type="radio" name="checktask" value="true"
                    <c:if test="${requestScope.done eq 'true'}">
                           checked
                    </c:if>
                >Done
                <br>
                <input type="radio" name="checktask" value="false"
                    <c:if test="${requestScope.done eq 'false'}">
                           checked
                    </c:if>
                >Undone
                <br><br>
                <button type="submit" class="btn btn-primary">Save</button>
            </form>

        </div>

    </div>
</div>

<jsp:include page="/WEB-INF/fragments/footer.jspf"/>

</body>
</html>