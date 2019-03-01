<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" type="text/css" href="styles.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
</head>
<body>

<div class="jumbotron text-center">
    <h1>ToDo App</h1>
    <p>Save your task</p>
</div>


<div class="taskslist">
<h2>Lista zadań</h2>

<form action="taskslist.jsp" method="get">
    Filtry:
    <select name="filter">
        <option></option>
        <option value="true">Wykonane</option>
        <option value="false">Niewykonane</option>
        <option value="date">Data</option>
    </select>
    <input type="date" name="dateFilter" value="<c:out value="${LocalDate.now()}"/>">
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


<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h3>Menu</h3>
            <c:choose>
            <c:when test="${empty sessionScope.username}">
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a class="nav-link active" href="register.jsp">Zarejestruj się</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="login.jsp">Zaloguj się</a>
                </li>
             </c:when>
             <c:otherwise>
                <li class="nav-item">
                    <a class="nav-link" href="userpanel.jsp">Panel użytkownika</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="logout">Wyloguj się</a>
                </li>
             </c:otherwise>
             </c:choose>
             <h3>Zadania</h3>
                <li class="nav-item">
                    <a class="nav-link" href="addtask.jsp">Dodaj zadanie</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="taskslist.jsp">Lista zadań</a>
                </li>
            </ul>
        </div>
        <div class="col-sm-4">
            <h3>Task list</h3>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Lp</th>
                    <th scope="col">Nazwa</th>
                    <th scope="col">Data wykonania</th>
                    <th scope="col">Opis</th>
                    <th scope="col">Wykonane</th>
                    <th scope="col">Operacja</th>
                </tr>
                </thead>



    <c:set var="service" value="${TaskServiceImpl}"/>
    <c:set var="username" value="${sessionScope.username}"/>
    <c:set var="taskList" value="${service.getAllTasksByUserId(username)}"/>

    <%
        System.out.println( "service = " + pageContext.findAttribute("service"));
        System.out.println( "taskList = " + pageContext.findAttribute("taskList"));
    %>

    <c:if test="${not empty taskList}">

        <c:set var="filter" value="${requestScope.filter}"/>
        <c:if test="${not empty filter}">

            <c:set var="filterOption" value="${FilterOption.valueOf(filter.toUpperCase())}"/>
            <c:choose>
                <c:when test="${filterOption.equals('DATE')}">
                    <c:set var="date" value="${LocalDate.parse(requestScope.dateFilter)}}"/>
                    <c:set var="taskList" value="${service.getAllTasksByDate(username, date)}"/>
                </c:when>
                <c:when test="${filterOption.equals('TRUE')}">
                    <c:set var="taskList" scope="session" value="${service.getAllTasksByTaskDone(username, true)}"/>
                </c:when>
                <c:when test="${filterOption.equals('FALSE')}">
                    <c:set var="taskList" scope="session" value="${service.getAllTasksByTaskDone(username, false)}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="taskList" scope="session" value="${service.getAllTasksByUserId(username)}"/>
                </c:otherwise>
            </c:choose>

        </c:if>

        <c:set var="sort" value="${requestScope.sort}"/>
        <c:if test="${not empty sort}">

            <c:set var="orderOption" value="${OrderOption.valueOf(sort.toUpperCase())}"/>
            <c:choose>
                <c:when test="${orderOption.equals('TITLE')}">
                    <c:set var="taskList" scope="session" value="${service.getAllTasksOrderedByTitle(username)}"/>
                </c:when>
                <c:when test="${orderOption.equals('DATE')}">
                    <c:set var="taskList" scope="session" value="${service.getAllTasksOrderedByDate(username)}"/>
                </c:when>
                <c:when test="${orderOption.equals('STATUS')}">
                    <c:set var="taskList" scope="session" value="${service.getAllTasksOrderedByStatus(username)}"/>
                </c:when>
            </c:choose>

        </c:if>

        <c:set var="row" value="1"/>
        <c:forEach var="task" items="${taskList}">

            <tbody>
            <tr>
                <td><c:out value="${row}"/></td>
                <td><c:out value="${task.getTitle()}"/></td>
                <td>@<c:out value="${task.getDate}"/></td>
                <td><c:out value="${task.getDescription}"/></td>
                <td><c:out value="${task.getTaskDone}"/></td>
                <td>
                    <p><a href="getTaskInfo?id=<c:out value="${task.getTaskId}"/>&title=<c:out value="${task.getTitle()}"/>&date=<c:out value="${task.getDate()}"/>&description=<c:out value="${task.getDescription()}"/>&done=<c:out value="${task.getTaskDone()}"/>">>Edytuj</a></p>
                    <p><a href="deleteTask?id=<c:out value="${task.getTaskId()}"/>">Usuń</a></p>
                </td>
            </tr>
            </tbody>

            <c:set var="row" value="${row+1}"/>
        </c:forEach>

        </c:if>

    </table>
    </div>
    </div>
</div>
</div>
</body>
</html>
