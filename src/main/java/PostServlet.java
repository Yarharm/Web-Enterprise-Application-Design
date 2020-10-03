import business_layer.ChatManager;
import business_layer.ChatMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "PostServlet")
public class PostServlet extends HttpServlet {
    ChatManager c = new ChatManager();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String message = request.getParameter("message");

        if(userName == "" || userName == null){ //if the username is not filled in, user will be named anonymous
            userName = "Anonymous";
        }

        c.postMessage(userName, message);

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<p>Please enter your username and a message</p>\n" +
                "<form method = \"POST\" action = \"PostServlet\">\n" +
                "    <p>\n" +
                "        Username <input type=\"text\" name=\"userName\">\n" +
                "    </p>\n" +
                "    <p>\n" +
                "        Message <input type=\"text\" name=\"message\">\n" +
                "    </p>\n" +
                "    <p>\n" +
                "        <input type=\"submit\" value=\"Submit\" name=\"B1\">\n" +
                "    </p>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");

        out.println("<h3>The following message has been added:</h3>");
        out.println("<p>Username: " + userName + ", Message: " + message);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<p>Please enter your username and a message</p>\n" +
                "<form method = \"POST\" action = \"PostServlet\">\n" +
                "    <p>\n" +
                "        Username <input type=\"text\" name=\"userName\">\n" +
                "    </p>\n" +
                "    <p>\n" +
                "        Message <input type=\"text\" name=\"message\">\n" +
                "    </p>\n" +
                "    <p>\n" +
                "        <input type=\"submit\" value=\"Submit\" name=\"B1\">\n" +
                "    </p>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
    }
}
