<%@ page import="pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.Task" %>
<%@ page import="pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service.FilterOption" %>
<%@ page import="pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service.OrderOption" %>
<%@ page import="pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service.TaskServiceImpl" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title>Task list</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/fragments/menu.jspf"/>

<div class="row">

    <div class="col-sm-6" style="background-color:lavender;">

        <div class="form-group">
            <form class="form-inline" action="taskslist.jsp" method="get">
                <label for="filters">Filters </label>
                <select class="form-control" id="filters" name="filter">
                    <option></option>
                    <option value="true">Checked</option>
                    <option value="false">Unchecked</option>
                    <option value="date">Date</option>
                </select>
                <br>
                <input type="date" name="dateFilter" value="<c:out value="${LocalDate.now()}"/>">
                <br>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
            <br>

        </div>

    </div>

    <div class="col-sm-6" style="background-color:lavender;">
        <form class="form-inline" action="taskslist.jsp" method="get">
            <label for="sort">Sort </label>
            <select class="form-control" id="sort" name="sort">
                <option></option>
                <option value="title">Title</option>
                <option value="date">Checked</option>
                <option value="status">Date</option>
            </select>
            <br>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
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

        <%
            TaskServiceImpl service = TaskServiceImpl.getInstance();
            String username = (String) request.getSession(false).getAttribute("username");
            List<Task> tasksList = service.getAllTasksByUserId(username);
            if(tasksList != null)
            {
                String filter = request.getParameter("filter");
                if(filter != null && !filter.isEmpty())
                {
                    FilterOption filterOption = FilterOption.valueOf(filter.toUpperCase());
                    switch (filterOption) {
                        case DATE:
                            LocalDate date = LocalDate.parse(request.getParameter("dateFilter"));
                            tasksList = service.getAllTasksByDate(username, date);
                            break;
                        case TRUE:
                            tasksList = service.getAllTasksByTaskDone(username, true);
                            break;
                        case FALSE:
                            tasksList = service.getAllTasksByTaskDone(username, false);
                            break;
                        default:
                            tasksList = service.getAllTasksByUserId(username);
                            break;
                    }
                }
                String sort = request.getParameter("sort");
                if(sort != null && !sort.isEmpty())
                {
                    OrderOption orderOption = OrderOption.valueOf(sort.toUpperCase());
                    switch (orderOption) {
                        case TITLE:
                            tasksList = service.getAllTasksOrderedByTitle(username);
                            break;
                        case DATE:
                            tasksList = service.getAllTasksOrderedByDate(username);
                            break;
                        case STATUS:
                            tasksList = service.getAllTasksOrderedByStatus(username);
                            break;
                    }
                }
                int row = 1;
                for(Task task : tasksList)
                {
        %>


        <tbody>
        <tr>
            <th scope="row"><%=row%></th>
            <td><%=task.getTitle()%></td>
            <td><%=task.getDate()%></td>
            <td>@<%=task.getDescription()%></td>
            <td><%=task.getTaskDone()%></td>
            <td>
                <p><a href="getTaskInfo?id=<%=task.getTaskId()%>&title=<%=task.getTitle()%>&date=<%=task.getDate()%>&description=<%=task.getDescription()%>&done=<%=task.getTaskDone()%>"><button type="button">Edytuj</button></a></p>
                <p><a href="deleteTask?id=<%=task.getTaskId()%>"><button type="button">Usuń</button></a></p>
            </td>
        </tr>
        </tbody>

        <%
                    row++; } }
        %>

    </table>

</div>


</body>
</html>
