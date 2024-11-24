package com.vector.myfriend.servlet;

import com.vector.myfriend.model.FriendRequest;
import com.vector.myfriend.model.User;
import com.vector.myfriend.service.FriendRequestService;
import com.vector.myfriend.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/sendRequest")

public class SendFriendRequestServlet extends HttpServlet {

    private UserService userService = new UserService();
    private FriendRequestService friendRequestService = new FriendRequestService();


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User sender = (User)req.getSession().getAttribute("user");
        User recipient = userService.getUserByEmail(req.getParameter("recipientEmail"));
        Date date = new Date();
        FriendRequest friendRequest = FriendRequest.builder()
                .sender(sender)
                .recipient(recipient)
                .date(date)
                .build();

        friendRequestService.add(friendRequest);
        req.getSession().setAttribute("msg", "You have sent a friendship request to " + recipient.getEmail());
        resp.sendRedirect("/");
    }
}
