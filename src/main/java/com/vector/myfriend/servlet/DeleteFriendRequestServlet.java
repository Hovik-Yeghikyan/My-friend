package com.vector.myfriend.servlet;


import com.vector.myfriend.model.FriendRequest;
import com.vector.myfriend.service.FriendRequestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteFriendRequest")
public class DeleteFriendRequestServlet extends HttpServlet {

    private FriendRequestService friendRequestService = new FriendRequestService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int requestId = Integer.parseInt(req.getParameter("requestId"));
        FriendRequest friendRequest = friendRequestService.getFriendRequestById(requestId);
        if (friendRequest == null) {
            resp.sendRedirect("/users");
        } else {
            friendRequestService.deleteFriendRequest(friendRequest.getId());
            resp.sendRedirect("/users");
        }
    }
}