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
import java.util.Set;

import static helpers.Constants.*;

@WebServlet(name = "PostDeleteServlet")
public class PostDeleteServlet extends HttpServlet {
    MessageBoardManager boardManager = new MessageBoardManager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postIDParam = request.getParameter("postID");
        try {
            if(postIDParam == null || postIDParam.isEmpty()) {
                throw new Exception("Post delete failed! Missing postID");
            }

            int postID = Integer.parseInt(postIDParam);
            int userID = (int) request.getSession().getAttribute("userID");
            Set<String> groupMemberships = (Set<String>) request.getSession().getAttribute(GROUP_MEMBERSHIP_SESSION_ATTRIBUTE);
            if(!boardManager.isPostOwner(userID, postID) && !groupMemberships.contains("admins")) {
                throw new Exception("Post delete failed! Permission denied");
            }

            boolean deleteStatus = boardManager.deletePost(postID);
            if(!deleteStatus) {
                throw new Exception("Post delete failed! Post does not exist");
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
