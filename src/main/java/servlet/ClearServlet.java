package servlet;

import business_layer.ChatManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static helpers.SharedConstants.CHAT_PAGE;
import static helpers.SharedConstants.DISPLAY_WARNING_POPUP;
import static helpers.Utils.parseDateTimeLocal;

import business_layer.ChatMessage;
import helpers.FrontendChatManager;

@WebServlet(name = "ClearServlet")
public class ClearServlet extends HttpServlet {
    private final ChatManager chatManager = ChatManager.getChatManagerInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateFrom = request.getParameter("dateFrom");
        String dateTo = request.getParameter("dateTo");
        String clearAll = request.getParameter("clearAll");
        try {
            Date startDate = new Date(0);
            Date endDate = new Date(System.currentTimeMillis());
            if(clearAll == null && !dateFrom.isEmpty()) {
                startDate = parseDateTimeLocal(dateFrom);
            }
            if(clearAll == null && !dateTo.isEmpty()) {
                endDate = parseDateTimeLocal(dateTo);
            }
            if(endDate.before(startDate)) {
                throw new Exception("End date is prior start date");
            }
            List<ChatMessage> filteredMessages = chatManager.ClearMessages(startDate.getTime(), endDate.getTime());
            FrontendChatManager.filterChatWindow(request, filteredMessages);
        } catch (Exception e) {
            request.getServletContext().setAttribute(DISPLAY_WARNING_POPUP, "Warning! Start date can not be prior an end date!");
        } finally {
            response.sendRedirect(CHAT_PAGE);
        }
    }
}
