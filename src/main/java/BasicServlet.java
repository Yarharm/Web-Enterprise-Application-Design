import business_layer.ChatManager;
import business_layer.ChatMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "BasicServlet")
public class BasicServlet extends HttpServlet {
    private final ChatManager chatManager = new ChatManager();
    private static final String WELCOME_PAGE = "index.jsp";
    private static final String CHAT_WINDOW_APPLICATION_ATTRIBUTE = "chatWindow";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd\'T\'hh:mm");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String message = request.getParameter("message");

        List<ChatMessage> chatMessage = chatManager.PostMessage(userName, message);
        this.appendChatWindow(request, chatMessage);
        response.sendRedirect(WELCOME_PAGE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setDateHeader("Expires", 0);

        if (request.getParameter("format")==null) {
            response.setContentType("text/plain");
            response.setHeader("Content-disposition", "attachment; filename=messages.txt");
        } else {
            response.setContentType("text/xml");
            response.setHeader("Content-disposition", "attachment; filename=messages.xml");
        }

        String startDateTime = request.getParameter("startTime");
        String endDateTime = request.getParameter("endTime");


        Date start = null;
        Date end = null;

        if (startDateTime.isEmpty()) {
            start = new Date(0);
        } else {
            try {
                start = sdf.parse(startDateTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (endDateTime.isEmpty()){
            end = new Date(System.currentTimeMillis());
        } else {
            try {
                end = sdf.parse(endDateTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        PrintWriter out = response.getWriter();
        List<ChatMessage> allMessages = chatManager.ListMessages(start.getTime(), end.getTime());

        if (request.getParameter("format")==null){
            for (ChatMessage c : allMessages){
                out.println(c.toString());
            }
        } else {
            out.println("<chat_messages>");
            for (ChatMessage c : allMessages){
                out.println("\t<message>");
                out.println("\t\t<username>" + c.getUser() + "</username>");
                out.println("\t\t<message_body>" + c.getMessage() + "</message_body>");
                out.println("\t\t<date>" + c.getTimestamp() + "</date>");
                out.println("\t</message>");
            }
            out.println("</chat_messages>");
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
