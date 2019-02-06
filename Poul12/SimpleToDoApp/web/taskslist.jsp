<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.Task" %>
<%@ page import="java.util.List" %>
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
            text-align: center;
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
        <td>
            <form action="checkTask" method="post">
            <input type="checkbox" name="checktask" value="yes"
                <% if(task.getTaskDone().equals("yes")){%>
                   checked
                <%}%>>Wykonane
            <input type="hidden" name="task_id" value="<%=task.getId()%>">
            <input type="submit" value="Potwierdź">
            </form>
        </td>
        <td>
            <p><a href="getTaskInfo?id=<%=task.getId()%>&title=<%=task.getTitle()%>&date=<%=task.getDate()%>&description=<%=task.getDescription()%>"><button type="button">Edytuj</button></a></p>
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
