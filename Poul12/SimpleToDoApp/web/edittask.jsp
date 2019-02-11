<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit task</title>
    <style>
        body
        {
            background-color: #b3daff;
        }

        h1
        {
            color: white;
            text-align: center;
        }

        div
        {
            font-family: verdana;
            font-size: 14px;
            text-align: center;
            margin-left: 25px;
        }

        p
        {
            font-family: verdana;
            font-size: 16px;
            text-align: right;
        }

        form
        {
            text-align: center;
            border-style: solid;
            border-width: 3px;
            border-color: #80c1ff;
            background-color: white;
            margin: 10px 500px 50px 500px;

        }

    </style>
</head>
<body>

<h1>Edytuj zadanie</h1>

<p><a href="index.jsp"><button type="button">Menu</button></a></p>

<br>
<form action="editTask" method="post" id="taskform">
    <br>
    <div>Nazwa zadania:</div>
    <input type="text" name="title" size="50" placeholder="Nazwa zadania" value="<%=request.getAttribute("title")%>">
    <br><br>
    <div>Data:</div>
    <input type="date" name="taskdate" value="<%=request.getAttribute("date")%>">
    <br><br>
    <div>Opis:</div>
    <textarea rows="5" cols="50" name="description" wrap="hard" maxlength ="150" placeholder="Opis zadania... (max 150 znaków)"><%=request.getAttribute("description")%></textarea>
    <br><br>
    <input type="checkbox" name="checktask" value="yes" <%
                                            if(request.getAttribute("done").equals("yes")){%>
                                                checked
                                            <%}%>>Wykonane
    <input type="hidden" name="task_id" value="<%=request.getAttribute("id")%>">
    <br><br>
    <input type="submit" value="Zaktualizuj zadanie">
    <br><br>
</form>

</body>
</html>