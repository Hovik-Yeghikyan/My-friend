<%@ page import="java.util.List" %>
<%@ page import="com.vector.myfriend.model.User" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<html>
<head>
    <title>User List</title>
</head>
<body>
<h2>Users List</h2>
<a href="/notifications">Notifications</a><br>
<table border="1">
    <thead>
    <tr>
        <th>Image</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Email</th>
        <th>Send Friend Request</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<User> users = (List<User>) request.getAttribute("users");
        for (User user : users) {
    %>
    <tr>
        <td>
            <% if (user.getImgName() != null && !user.getImgName().isEmpty()) { %>
            <img src="/getImage?imageName=<%= user.getImgName() %>" width="50" />
            <% } else { %>
            <span>No image</span>
            <% } %>
        </td>
        <td><%= user.getName() %></td>
        <td><%= user.getSurname() %></td>
        <td><%= user.getEmail() %></td>
        <td>

            <form action="/sendRequest" method="post">
                <input type="hidden" name="recipientEmail" value="<%= user.getEmail() %>" />
                <button type="submit">Send Request</button>
            </form>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>