import business_layer.ChatManager;
import business_layer.ChatMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BasicServlet")
public class BasicServlet extends HttpServlet {
    private final ChatManager chatManager = new ChatManager();
    private static final String WELCOME_PAGE = "index.jsp";
    private static final String CHAT_WINDOW_APPLICATION_ATTRIBUTE = "chatWindow";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String message = request.getParameter("message");

        List<ChatMessage> chatMessage = chatManager.PostMessage(userName, message);
        this.appendChatWindow(request, chatMessage);
        response.sendRedirect(WELCOME_PAGE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setHeader("Content-disposition", "attachment; filename=messages.txt");

        PrintWriter out = response.getWriter();
        List<ChatMessage> allMessages = chatManager.ListMessages(Long.valueOf(0), System.currentTimeMillis());

        for (ChatMessage c : allMessages){
            out.println(c.toString());
        }
    }

    private void appendChatWindow(HttpServletRequest request, List<ChatMessage> chatMessage) {
        List<ChatMessage> chatWindow = fetchChatWindow(request);
        chatWindow.addAll(chatMessage);
    }

    private List<ChatMessage> fetchChatWindow(HttpServletRequest request) {
        List<ChatMessage> chatWindow = (List<ChatMessage>) request.getServletContext().getAttribute(CHAT_WINDOW_APPLICATION_ATTRIBUTE);
        if(chatWindow == null) {
            chatWindow = new ArrayList<>();
            request.getServletContext().setAttribute("chatWindow", chatWindow);
        }
        return chatWindow;
    }
}
