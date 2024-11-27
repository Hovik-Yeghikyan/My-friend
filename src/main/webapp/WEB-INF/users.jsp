<%@ page import="java.util.List" %>
<%@ page import="com.vector.myfriend.model.User" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<html>
<head>
    <title>My Profile</title>
</head>
<body>
<%
    User currentUser = (User) session.getAttribute("user");
    List<User> users = (List<User>) request.getAttribute("users");
%>
Welcome <%=currentUser.getName()%> | <a href="/logout">Logout</a>
<img src="/getImage?imageName=<%=currentUser.getImgName()%>" width="50">
<ul>
    <li><a href="/users">View All Users</a></li>
    <li><a href="/myFriendRequests">View My Requests</a></li>
    <li><a href="/mySentFriendRequests">View My Sent Requests</a></li>
    <li><a href="/myFriends">View My Friends</a></li>
    <li><a href="/myMessages">View My Messages</a></li>
    <li><a href="/mySentMessages">View My Sent Messages</a></li>
</ul>
<div>
    <table>
        <tr>
            <th>image</th>
            <th>name</th>
            <th>surname</th>
            <th>action</th>
        </tr>
        <%
            for (User user : users) { %>
        <tr>
            <td>
                <img src="/getImage?imageName=<%=user.getImgName()%>" width="40">
            </td>
            <td><%=user.getName()%>
            </td>
            <td><%=user.getSurname()%>
            </td>
            <td><a href="/sendFriendRequest?friendId=<%=user.getId()%>">Send Request</a></td>
        </tr>
        <%}%>

    </table>
</div>
</body>
</html>