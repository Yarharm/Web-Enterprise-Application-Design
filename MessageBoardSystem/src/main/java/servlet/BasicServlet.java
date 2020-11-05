package servlet;

import business_layer.MessageBoardManager;
import helpers.FrontendBoardManager;
import models.Post;

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

        if(message != null && title != null && !message.isEmpty() && !title.isEmpty()) {
            Post post = boardManager.postMessage(userID, title, message);
            FrontendBoardManager.appendMessageBoard(request, post);
        }

        response.sendRedirect(ROOT_PAGE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
