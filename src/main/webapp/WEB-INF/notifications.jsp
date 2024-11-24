<%@ page import="com.vector.myfriend.model.FriendRequest" %>
<%@ page import="java.util.List" %>
<%@ page import="com.vector.myfriend.model.User" %><%--
  Created by IntelliJ IDEA.
  User: hovoe
  Date: 24.11.2024
  Time: 3:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NOTIFICATIONS</title>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    List<FriendRequest> requests = (List<FriendRequest>) request.getAttribute("friendRequests");

    boolean hasNotifications = false;

    for (FriendRequest friendRequest : requests) {
        if (friendRequest.getRecipient() != null && friendRequest.getSender() != null) {
            if (user.getEmail().equals(friendRequest.getRecipient().getEmail())) {
                hasNotifications = true;

%>
<span>
        <h3>You have a friend request from <%= friendRequest.getSender().getEmail() %></h3>
        <form action="/addFriends" method="post">
            <input type="hidden" name="sender" value="<%= friendRequest.getSender().getEmail() %>"/>
            <button type="submit">Accept</button>
        </form>
    </span>
<%
            }
        }
    }
    if (!hasNotifications) {
%>
<h3>No notifications</h3>
<%
    }
%>
</body>
</html>