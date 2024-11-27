<%@ page import="com.vector.myfriend.model.User" %>
<%@ page import="com.vector.myfriend.model.FriendRequest" %>
<%@ page import="java.util.List" %>
<%@ page import="com.vector.myfriend.util.DateUtil" %><%--
  Created by IntelliJ IDEA.
  User: hovoe
  Date: 27.11.2024
  Time: 2:50
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
  List<FriendRequest> sentFriendRequestList = (List<FriendRequest>) request.getAttribute("sentFriendRequestList");
%>
Welcome <%=currentUser.getName()%> | <a href="/logout">Logout</a>
<%if (currentUser.getImgName() != null && !currentUser.getImgName().isEmpty()) {%>
<img src="/getImage?imageName=<%=currentUser.getImgName()%>" width="50">
<%}%>
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
      <th>date</th>
      <th>action</th>
    </tr>
    <%
      for (FriendRequest friendRequest : sentFriendRequestList) { %>
    <tr>
      <td>
        <%if (friendRequest.getToUser().getImgName() != null && !friendRequest.getToUser().getImgName().isEmpty()) {%>
        <img src="/getImage?imageName=<%=friendRequest.getToUser().getImgName()%>" width="40">
        <%}%>
      </td>
      <td><%=friendRequest.getToUser().getName() + " " + friendRequest.getToUser().getSurname()%>
      </td>
      <td><%=DateUtil.fromDateToSqlDateTimeString(friendRequest.getDate())%>
      </td>
      <td><a href="/deleteFriendRequest?requestId=<%=friendRequest.getId()%>">Delete</a></td>
    </tr>
    <%}%>

  </table>
</div>
</body>
</html>