<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
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

<form action="tasks" method="get">
    Filtry:
    <select name="filter">
        <option></option>
        <option value="true">Wykonane</option>
        <option value="false">Niewykonane</option>
        <option value="date">Data</option>
    </select>
    <input type="date" name="dateFilter">
    <input type="submit" value="Filtruj">
</form>
<br>
<form action="tasks" method="get">
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
        @SuppressWarnings("unchecked")
        List<Task> tasksList = (List<Task>) request.getAttribute("tasksList");
        if(tasksList != null)
        {
            int row = 1;
            for(Task task : tasksList)
            {
    %>
    <tr>
        <td><%=row%></td>
        <td><%=task.getTitle()%></td>
        <td><%=task.getDate()%></td>
        <td><%=task.getDescription()%></td>
        <td><%=task.getTaskDone()%></td>
        <td>
            <p><a href="getTaskInfo?id=<%=task.getId()%>&title=<%=task.getTitle()%>&date=<%=task.getDate()%>&description=<%=task.getDescription()%>&done=<%=task.getTaskDone()%>"><button type="button">Edytuj</button></a></p>
            <p><a href="deleteTask?id=<%=task.getId()%>"><button type="button">Usuń</button></a></p>
        </td>
    </tr>
    <%
                row++; } }
    %>

</table>

<p><a href="tasks.jsp">Wróć</a></p>

</body>
</html>
