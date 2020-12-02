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
import java.util.List;

import static helpers.Constants.ROOT_PAGE;

@WebServlet(name = "PaginationServlet")
public class PaginationServlet extends HttpServlet {
    MessageBoardManager boardManager = new MessageBoardManager();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Post> recentPosts = this.boardManager.getAllPosts();
        FrontendBoardManager.refreshMessageBoard(request, recentPosts);
        FrontendBoardManager.paginateMessageBoard(request);
        response.sendRedirect(ROOT_PAGE);
    }
}
