
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Message</title>
    <style>

        body
        {
            background-color: #b3daff;
        }

        h3
        {
            color: #007599;
            text-align: center;
            background-color: #e6f9ff;
            border-style: outset;
            margin: 50px 500px 15px 500px;
        }

        button
        {
            font-family: verdana;
            font-size: 12px;
            text-align: center;
            margin: 15px 500px 50px 500px;

        }

        div
        {
            font-family: verdana;
            font-size: 16px;
            text-align: center;
        }

    </style>
</head>
<body>

<h3><%=request.getAttribute("message")%></h3>

<a href="index.jsp"><button type="button">Wróć</button></a>

</body>
</html>