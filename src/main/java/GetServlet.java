import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetServlet")
public class GetServlet extends HttpServlet {
    ChatManager c = new ChatManager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userNameInput = request.getParameter("userName");
        String messageInput = request.getParameter("message");

        if (messageInput!=null&&!messageInput.equals("")){  //if the message is not empty, post it
            if (userNameInput.equals("")) userNameInput = "Anonymous";  //if username is empty, use 'anonymous' as username
            c.postMessage(userNameInput, messageInput);
        }

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Demo</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Placeholder Title</h1>");

        out.println("<form method = \"GET\" action = \"\">\n" +
                "    <p>\n" +
                "        Username <input type=\"text\" name=\"userName\" size=\"20\">\n" +
                "    </p>\n" +
                "    <p>\n" +
                "        Message <input type=\"text\" name=\"message\" size=\"20\">\n" +
                "    </p>\n" +
                "    <p>\n" +
                "        <input type=\"submit\" value=\"Submit\" name=\"B1\">\n" +
                "    </p>\n" +
                "</form>");

        out.println("<h2>Messages</h2>");
        for (ChatManager.Message m : c.getMessages()){
            out.println("<p>User: " + m.userName +"<br>Message: "+ m.message + "</p>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}
