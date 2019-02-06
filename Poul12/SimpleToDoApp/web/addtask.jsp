<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create new task</title>
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

<h1>Stwórz nowe zadanie</h1>

<br>
<form action="addTask" method="post" id="taskform">
    <br>
    <div>Nazwa zadania:</div>
    <input type="text" name="title" size="50" placeholder="Nazwa zadania">
    <br><br>
    <div>Data:</div>
    <input type="date" name="taskdate">
    <br><br>
    <div>Opis:</div>
    <textarea rows="5" cols="50" name="description" wrap="hard" maxlength ="150" placeholder="Opis zadania... (max 150 znaków)"></textarea>
    <br><br>
    <input type="submit" value="Stwórz nowe zadanie">
    <br><br>
</form>

</body>
</html>