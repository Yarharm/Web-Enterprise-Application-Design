import business_layer.ChatManager;
import business_layer.ChatMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
<<<<<<< 69708ca62f3787fb529294f467ca469c028884a8
<<<<<<< 32082e31fd4a321eda069daf68dd748b08e65285
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
=======
>>>>>>> added doGet functionality
=======
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
>>>>>>> fixed commented issues
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "BasicServlet")
public class BasicServlet extends HttpServlet {
    private final ChatManager chatManager = new ChatManager();
    private static final String WELCOME_PAGE = "index.jsp";
    private static final String CHAT_WINDOW_APPLICATION_ATTRIBUTE = "chatWindow";
<<<<<<< 69708ca62f3787fb529294f467ca469c028884a8
    private static final String XML_ROOT_CHAT_MESSAGES_OPEN_TAG = "<chat_messages>";
    private static final String XML_ROOT_CHAT_MESSAGES_CLOSE_TAG = "</chat_messages>";
    private static final String XML_CHAT_MESSAGE_OPEN_TAG = "<message>";
    private static final String XML_CHAT_MESSAGE_CLOSE_TAG = "</message>";
    private static final String XML_USERNAME_OPEN_TAG = "<username>";
    private static final String XML_USERNAME_CLOSE_TAG = "</username>";
    private static final String XML_MESSAGE_CONTENT_OPEN_TAG = "<message_body>";
    private static final String XML_MESSAGE_CONTENT_CLOSE_TAG = "</message_body>";
    private static final String XML_DATE_OPEN_TAG = "<date>";
    private static final String XML_DATE_CLOSE_TAG  = "</date>";

=======
>>>>>>> fixed commented issues
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd\'T\'hh:mm");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String message = request.getParameter("message");

        List<ChatMessage> chatMessage = chatManager.PostMessage(userName, message);
        this.appendChatWindow(request, chatMessage);
        response.sendRedirect(WELCOME_PAGE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
<<<<<<< 69708ca62f3787fb529294f467ca469c028884a8
<<<<<<< 32082e31fd4a321eda069daf68dd748b08e65285
=======
>>>>>>> fixed commented issues
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


<<<<<<< 69708ca62f3787fb529294f467ca469c028884a8
        Date start = new Date(0);
        Date end = new Date(System.currentTimeMillis());

        if (!startDateTime.isEmpty()) {
=======
        Date start = null;
        Date end = null;

        if (startDateTime.isEmpty()) {
            start = new Date(0);
        } else {
>>>>>>> fixed commented issues
            try {
                start = sdf.parse(startDateTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

<<<<<<< 69708ca62f3787fb529294f467ca469c028884a8
        if (!endDateTime.isEmpty()){
=======
        if (endDateTime.isEmpty()){
            end = new Date(System.currentTimeMillis());
        } else {
>>>>>>> fixed commented issues
            try {
                end = sdf.parse(endDateTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
<<<<<<< 69708ca62f3787fb529294f467ca469c028884a8

        if (start.after(end)){
            Date temp = start;
            start = end;
            end = temp;
        }

        PrintWriter out = response.getWriter();
        List<ChatMessage> allMessages = chatManager.ListMessages(start.getTime(), end.getTime());

        if (request.getParameter("format")==null){
            for (ChatMessage c : allMessages){
                out.println(c.toString());
            }
        } else {
            out.println(XML_ROOT_CHAT_MESSAGES_OPEN_TAG);
            for (ChatMessage c : allMessages){
                out.println("\t"+XML_CHAT_MESSAGE_OPEN_TAG);
                out.println("\t\t"+ XML_USERNAME_OPEN_TAG+ c.getUser() + XML_USERNAME_CLOSE_TAG);
                out.println("\t\t" + XML_MESSAGE_CONTENT_OPEN_TAG + c.getMessage() + XML_MESSAGE_CONTENT_CLOSE_TAG);
                out.println("\t\t" + XML_DATE_OPEN_TAG + c.getTimestamp() + XML_DATE_OPEN_TAG);
                out.println("\t" + XML_CHAT_MESSAGE_CLOSE_TAG);
            }
            out.println(XML_ROOT_CHAT_MESSAGES_CLOSE_TAG);
=======
        response.setContentType("text/plain");
        response.setHeader("Content-disposition", "attachment; filename=messages.txt");
=======
>>>>>>> fixed commented issues

        PrintWriter out = response.getWriter();
        List<ChatMessage> allMessages = chatManager.ListMessages(start.getTime(), end.getTime());

<<<<<<< 1f61fee09325da4e09c0d9f731322ffcbcfddda7
        for (ChatMessage c : allMessages){
            out.println(c.toString());
>>>>>>> added doGet functionality
=======
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
>>>>>>> fixed xml formatting for message download, removed unneeded listMessages method overload
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
