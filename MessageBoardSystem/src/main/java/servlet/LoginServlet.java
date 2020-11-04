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

import static helpers.Constants.ROOT_PAGE;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    MessageBoardManager boardManager = new MessageBoardManager();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        User authUser = boardManager.loginUser(email, password);

        if(authUser != null && session.getAttribute("userID") == null) {
            session.setAttribute("userID", authUser.getUserID());
        }
        response.sendRedirect(ROOT_PAGE);
    }
}
