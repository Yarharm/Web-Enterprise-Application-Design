package servlet;

import business_layer.ChatManager;
import business_layer.ChatMessage;

import org.apache.commons.lang.StringEscapeUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import static helpers.SharedConstants.*;
import static helpers.Utils.parseDateTimeLocal;
import helpers.FrontendChatManager;

@WebServlet(name = "BasicServlet")
public class BasicServlet extends HttpServlet {
    private final ChatManager chatManager = ChatManager.getChatManagerInstance();
    private static final String XML_ROOT_CHAT_MESSAGES_OPEN_TAG = "<messages>";
    private static final String XML_ROOT_CHAT_MESSAGES_CLOSE_TAG = "</messages>";
    private static final String XML_CHAT_MESSAGE_OPEN_TAG = "<message>";
    private static final String XML_CHAT_MESSAGE_CLOSE_TAG = "</message>";
    private static final String XML_USERNAME_OPEN_TAG = "<username>";
    private static final String XML_USERNAME_CLOSE_TAG = "</username>";
    private static final String XML_MESSAGE_CONTENT_OPEN_TAG = "<message_body>";
    private static final String XML_MESSAGE_CONTENT_CLOSE_TAG = "</message_body>";
    private static final String XML_DATE_OPEN_TAG = "<date>";
    private static final String XML_DATE_CLOSE_TAG = "</date>";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String referrer = request.getHeader("referer");
            if(referrer == null || referrer.isEmpty()) {
                throw new Exception("ERROR! Referer header is not present!");
            }

            String userName = request.getParameter("userName");
            String message = request.getParameter("message");
            if(message == null || message.isEmpty()) {
                throw new Exception("Warning! Message can not be empty!");
            }
            String escapedMessage = StringEscapeUtils.escapeXml(message);
            String escapedUserName = StringEscapeUtils.escapeXml(userName);
            List<ChatMessage> chatMessages = chatManager.PostMessage(escapedUserName, escapedMessage);
            FrontendChatManager.appendChatWindow(request, chatMessages);
        } catch(Exception e) {
            request.getServletContext().setAttribute(DISPLAY_WARNING_POPUP, e.getMessage());
        } finally {
            response.sendRedirect(CHAT_PAGE);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String referrer = request.getHeader("referer");
            if(referrer == null || referrer.isEmpty()) {
                throw new Exception("ERROR! Referer header is not present!");
            }

            String startDateTime = request.getParameter("startTime");
            String endDateTime = request.getParameter("endTime");

            Date start = new Date(0);
            Date end = new Date(System.currentTimeMillis());
            if (!startDateTime.isEmpty()) {
                start = parseDateTimeLocal(startDateTime);
            }
            if (!endDateTime.isEmpty()){
                end = parseDateTimeLocal(endDateTime);
            }
            if (start.after(end)){
                throw new Exception("Warning! Start date can not be prior an end date!");
            }

            if (request.getParameter("format")==null) {
                response.setContentType("text/plain");
                response.setHeader("Content-disposition", "attachment; filename=messages.txt");
            } else {
                response.setContentType("text/xml");
                response.setHeader("Content-disposition", "attachment; filename=messages.xml");
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
                    out.println("\t\t" + XML_DATE_OPEN_TAG + c.getTimestamp() + XML_DATE_CLOSE_TAG);
                    out.println("\t" + XML_CHAT_MESSAGE_CLOSE_TAG);
                }
                out.println(XML_ROOT_CHAT_MESSAGES_CLOSE_TAG);
            }
        } catch(Exception e) {
            request.getServletContext().setAttribute(DISPLAY_WARNING_POPUP, e.getMessage());
            response.sendRedirect(CHAT_PAGE);
        }
    }
}
