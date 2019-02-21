<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service.TaskServiceImpl" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service.OrderOption" %>
<%@ page import="pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service.FilterOption" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tasks List</title>

    <style>

        body
        {
            background-color: #b3daff;
        }

        form
        {
            color: #007599;
        }

        table
        {
            font-family: verdana;
            font-size: 16px;
            text-align: center;
        }

    </style>

</head>
<body>

<h2>Lista zadań</h2>

<form action="taskslist.jsp" method="get">
    Filtry:
    <select name="filter">
        <option></option>
        <option value="true">Wykonane</option>
        <option value="false">Niewykonane</option>
        <option value="date">Data</option>
    </select>
    <input type="date" name="dateFilter" value="<%=LocalDate.now()%>">
    <input type="submit" value="Filtruj">
</form>
<br>
<form action="taskslist.jsp" method="get">
    Sortuj:
    <select name="sort">
        <option></option>
        <option value="title">Tytuł</option>
        <option value="date">Data</option>
        <option value="status">Status</option>
    </select>
    <input type="submit" value="Sortuj">
</form>
<br><br>

<table style="width:50%" border="1">
    <tr>
        <th>Lp</th>
        <th>Nazwa</th>
        <th>Data wykonania</th>
        <th>Opis</th>
        <th>Wykonane</th>
        <th>Operacja</th>
    </tr>

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
    <tr>
        <td><%=row%></td>
        <td><%=task.getTitle()%></td>
        <td><%=task.getDate()%></td>
        <td><%=task.getDescription()%></td>
        <td><%=task.getTask_done()%></td>
        <td>
            <p><a href="getTaskInfo?id=<%=task.getTask_id()%>&title=<%=task.getTitle()%>&date=<%=task.getDate()%>&description=<%=task.getDescription()%>&done=<%=task.getTask_done()%>"><button type="button">Edytuj</button></a></p>
            <p><a href="deleteTask?id=<%=task.getTask_id()%>"><button type="button">Usuń</button></a></p>
        </td>
    </tr>
    <%
                row++; } }
    %>

</table>

<p><a href="tasks.jsp">Wróć</a></p>

</body>
</html>
