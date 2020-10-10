package servlet;

import business_layer.ChatMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static helpers.SharedConstants.CHAT_PAGE;

@WebServlet(name = "TemplateServlet")
public class TemplateServlet extends HttpServlet {
    private final static String TEMPLATE_ATTRIBUTE = "template";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String template = (String) request.getServletContext().getAttribute(TEMPLATE_ATTRIBUTE);
        if(template == null) {
            request.getServletContext().setAttribute(TEMPLATE_ATTRIBUTE, "template");
        } else {
            request.getServletContext().removeAttribute(TEMPLATE_ATTRIBUTE);
        }
        response.sendRedirect(CHAT_PAGE);
    }
}
