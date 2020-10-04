import business_layer.ChatManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BasicServlet")
public class BasicServlet extends HttpServlet {
    ChatManager chatManager = new ChatManager();
    static final String ANONYMOUS_USER = "Anonymous";
    static final String WELCOME_FILE_NAME = "Web_Enterprise_Application_Design_war";
    static String WELCOME_FILE_URL;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WELCOME_FILE_URL = "http://" + request.getServerName() + ":" + request.getServerPort() + "/" + WELCOME_FILE_NAME + "/";
        
        String userName = request.getParameter("userName");
        String message = request.getParameter("message");

        if(userName.isEmpty()){
            userName = ANONYMOUS_USER;
        }

        chatManager.postMessage(userName, message);

        response.sendRedirect(WELCOME_FILE_URL);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
