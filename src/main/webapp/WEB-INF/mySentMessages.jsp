<%@ page import="com.vector.myfriend.model.User" %>
<%@ page import="com.vector.myfriend.model.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="com.vector.myfriend.util.DateUtil" %><%--
  Created by IntelliJ IDEA.
  User: hovoe
  Date: 27.11.2024
  Time: 3:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Profile</title>
</head>
<body>
<%
    User currentUser = (User) session.getAttribute("user");
    List<Message> messages = (List<Message>) request.getAttribute("messages");
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
            <th>to image</th>
            <th>to name</th>
            <th>message</th>
            <th>date</th>
        </tr>
        <%
            for (Message message : messages) { %>
        <tr>
            <td>
                <img src="/getImage?imageName=<%=message.getToUser().getImgName()%>" width="40">
            </td>
            <td><%=message.getToUser().getName() + " " + message.getToUser().getSurname()%>
            </td>
            <td><%=message.getMessage()%>
            </td>
            <td><%=DateUtil.fromDateToSqlDateTimeString(message.getDate())%>
            </td>
        </tr>
        <%}%>

    </table>
</div>
</body>
</html>
