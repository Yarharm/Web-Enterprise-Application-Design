package servlet;

import business_layer.MessageBoardManager;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static helpers.Constants.*;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    MessageBoardManager boardManager = new MessageBoardManager();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        Object userID = session.getAttribute("userID");

        try {
            if(userID != null) {
                throw new Exception("Error! User is already authenticated");
            }
            if(email == null || password == null || email.isEmpty() || password.isEmpty()) {
                throw new Exception("Login failed! Missing credentials");
            }
            User authUser = boardManager.loginUser(email, password);

            if(authUser == null) {
                throw new Exception("Login failed! Invalid credentials");
            }

            session.setAttribute("userID", authUser.getUserID());
        } catch (Exception e) {
            request.getSession().setAttribute(DISPLAY_WARNING_POPUP, e.getMessage());
        } finally {
            response.sendRedirect(ROOT_PAGE);
        }
    }
}
