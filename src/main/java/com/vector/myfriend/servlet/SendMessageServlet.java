package com.vector.myfriend.servlet;


import com.vector.myfriend.model.Message;
import com.vector.myfriend.model.User;
import com.vector.myfriend.service.MessageService;
import com.vector.myfriend.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/sendMessage")
public class SendMessageServlet extends HttpServlet {

    private MessageService messageService = new MessageService();
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/sendMessage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int toId = Integer.parseInt(req.getParameter("to_id"));
        User fromUser = (User) req.getSession().getAttribute("user");
        String message = req.getParameter("message");
        messageService.add(Message.builder()
                .toUser(userService.getUserById(toId))
                .fromUser(fromUser)
                .message(message)
                .date(new Date())
                .build());
        resp.sendRedirect("/mySentMessages");
    }
}