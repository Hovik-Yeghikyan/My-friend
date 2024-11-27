<%@ page import="com.vector.myfriend.model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Profile</title>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
%>
Welcome <%=user.getName()%> | <a href="/logout">Logout</a>
<img src="/getImage?imageName=<%=user.getImgName()%>" width="50">
<ul>
    <li><a href="/users">View All Users</a></li>
    <li><a href="/myFriendRequests">View My Requests</a></li>
    <li><a href="/mySentFriendRequests">View My Sent Requests</a></li>
    <li><a href="/myFriends">View My Friends</a></li>
    <li><a href="/myMessages">View My Messages</a></li>
    <li><a href="/mySentMessages">View My Sent Messages</a></li>
</ul>
</body>
</html>