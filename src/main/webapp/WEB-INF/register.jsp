<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<span>
    <%
        if (session.getAttribute("msg") != null) {%>
    <h3 style="color: red"> <%=session.getAttribute("msg")%></h3>
    <%
            session.removeAttribute("msg");
        }
    %>
</span>
<form action="/register" method="post" enctype="multipart/form-data">
    name:<input type="text" name="name"><br>
    surname:<input type="text" name="surname"><br>
    email:<input type="text" name="email"><br>
    password:<input type="password" name="password"><br>
    <input type="file" name="img"><br>
    <input type="submit" value="Register">
</form>
</body>
</html>
