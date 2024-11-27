package com.vector.myfriend.servlet;

import com.vector.myfriend.model.Message;
import com.vector.myfriend.model.User;
import com.vector.myfriend.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/mySentMessages")
public class MySentMessagesServlet extends HttpServlet {

    private MessageService messageService = new MessageService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = ((User) req.getSession().getAttribute("user")).getId();
        List<Message> messagesByFromId = messageService.getMessagesByFromId(userId);

        req.setAttribute("messages", messagesByFromId);
        req.getRequestDispatcher("/WEB-INF/mySentMessages.jsp").forward(req, resp);
    }
}
