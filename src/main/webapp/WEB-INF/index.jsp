<%@ page import="com.vector.myfriend.model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>MY-FRIEND</title>
</head>
<body>
<h1>MY FRIEND APPLICATION</h1><%
    if (session.getAttribute("msg") != null) {%>
<h3 style="color: green"> <%=session.getAttribute("msg")%></h3>
<%
        session.removeAttribute("msg");
    }
%>
<%User user = (User) session.getAttribute("user");%>
<%
  if (user != null){%>
<span>Welcome<br>
    <%=user.getName() + " " + user.getSurname()%></span> <a href="/logout">LogOut</a> <br>
<%}else {%>
<a href="/login">Login</a><br>
<a href="/register">REGISTER</a><br>
<%}%>

<br/>
<a href="/friends">My Friends</a>
<a href="/users">View All Users</a>
</body>
</html>