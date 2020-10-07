package servlet;

import business_layer.ChatManager;
import business_layer.ChatMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static helpers.SharedConstants.CHAT_PAGE;
import helpers.FrontendChatManager;

@WebServlet(name = "BasicServlet")
public class BasicServlet extends HttpServlet {
    private final ChatManager chatManager = ChatManager.getChatManagerInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String message = request.getParameter("message");

        List<ChatMessage> chatMessages = chatManager.PostMessage(userName, message);
        FrontendChatManager.appendChatWindow(request, chatMessages);
        response.sendRedirect(CHAT_PAGE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
