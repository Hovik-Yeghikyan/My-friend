package com.vector.myfriend.servlet;

import com.vector.myfriend.model.User;
import com.vector.myfriend.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addFriends")
public class AddFriendsServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User recipient = (User)req.getSession().getAttribute("user");
        User sender = userService.getUserByEmail(req.getParameter("sender"));
        userService.addFriend(sender, recipient);
        req.getSession().setAttribute("msg", "You have accepted a friendship from " + sender.getEmail());
    }
}
