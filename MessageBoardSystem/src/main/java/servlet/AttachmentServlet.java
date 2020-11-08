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

import static helpers.Constants.DISPLAY_WARNING_POPUP;
import static helpers.Constants.ROOT_PAGE;

@WebServlet(name = "AttachmentServlet")
public class AttachmentServlet extends HttpServlet {
    MessageBoardManager boardManager = new MessageBoardManager();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postIDParam = request.getParameter("postID");
        try {
            if(postIDParam == null || postIDParam.isEmpty()) {
                throw new Exception("Attachment delete failed! Missing postID");
            }

            int postID = Integer.parseInt(postIDParam);
            int userID = (int) request.getSession().getAttribute("userID");
            if(!boardManager.isPostOwner(userID, postID)) {
                throw new Exception("Attachment delete failed! Permission denied");
            }

            Post post = boardManager.removeAttachment(postID);

            if(post == null) {
                throw new Exception("Attachment delete failed! Post does not exists");
            }

            FrontendBoardManager.updatePost(request, post);

        } catch (Exception e) {
            request.getSession().setAttribute(DISPLAY_WARNING_POPUP, e.getMessage());
        } finally {
            response.sendRedirect(ROOT_PAGE);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
