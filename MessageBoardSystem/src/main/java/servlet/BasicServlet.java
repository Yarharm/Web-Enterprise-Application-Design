package servlet;

import business_layer.MessageBoardManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static helpers.Constants.ROOT_PAGE;

@WebServlet(name = "BasicServlet")
public class BasicServlet extends HttpServlet {
    MessageBoardManager boardManager = new MessageBoardManager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        String title = request.getParameter("title");
        int userID = (int) request.getSession().getAttribute("userID");

        boardManager.postMessage(userID, title, message);

        response.sendRedirect(ROOT_PAGE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
