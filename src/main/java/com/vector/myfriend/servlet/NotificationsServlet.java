package com.vector.myfriend.servlet;

import com.vector.myfriend.model.FriendRequest;
import com.vector.myfriend.service.FriendRequestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/notifications")
public class NotificationsServlet extends HttpServlet {

    FriendRequestService friendRequestService = new FriendRequestService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FriendRequest> friendRequests = friendRequestService.getAllRequests();
        req.setAttribute("friendRequests", friendRequests);
        req.getRequestDispatcher("/WEB-INF/notifications.jsp").forward(req, resp);
    }
}
