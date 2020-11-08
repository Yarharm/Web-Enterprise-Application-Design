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

import static helpers.Constants.DISPLAY_WARNING_POPUP;
import static helpers.Constants.ROOT_PAGE;

@WebServlet(name = "PostDeleteServlet")
public class PostDeleteServlet extends HttpServlet {
    MessageBoardManager boardManager = new MessageBoardManager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postID = request.getParameter("postID");
        try {
            if(postID == null || postID.isEmpty()) {
                throw new Exception("Delete failed! Missing postID");
            }
            boolean deleteStatus = boardManager.deletePost(Integer.parseInt(postID));
            if(!deleteStatus) {
                throw new Exception("Delete failed! Post does not exist");
            } else {
                List<Post> freshPosts = boardManager.getAllPosts();
                FrontendBoardManager.refreshMessageBoard(request, freshPosts);
            }

        } catch (Exception e) {
            request.getSession().setAttribute(DISPLAY_WARNING_POPUP, e.getMessage());
        } finally {
            response.sendRedirect(ROOT_PAGE);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
