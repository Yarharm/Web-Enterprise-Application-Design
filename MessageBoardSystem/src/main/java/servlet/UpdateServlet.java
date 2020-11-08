package servlet;

import business_layer.MessageBoardManager;
import helpers.FrontendBoardManager;
import models.Post;
import org.apache.commons.lang.StringEscapeUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;

import static helpers.Constants.*;

@WebServlet(name = "UpdateServlet")
@MultipartConfig
public class UpdateServlet extends HttpServlet {
    MessageBoardManager messageBoardManager = new MessageBoardManager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String message = request.getParameter("message");
        Part attachmentPart = request.getPart("attachment");
        Object postAttribute = request.getSession().getAttribute("referredPost");

        try {
            if(title == null || title.isEmpty() || message == null || message.isEmpty() || postAttribute == null) {
                throw new Exception("Edit failed! Invalid attributes");
            }
            
            int userID = (int) request.getSession().getAttribute("userID");
            Post post = (Post) postAttribute;
            if(!messageBoardManager.isPostOwner(userID, post.getPostID())) {
                throw new Exception("Edit failed! Permission denied");
            }

            String escapedTitle = StringEscapeUtils.escapeXml(title);
            String escapedMessage = StringEscapeUtils.escapeXml(message);
            post.setPostTitle(escapedTitle);
            post.setMessage(escapedMessage);
            if(attachmentPart != null && attachmentPart.getSize() > 0) {
                messageBoardManager.updateAttachment(post, attachmentPart.getSubmittedFileName(), attachmentPart.getSize(),
                        attachmentPart.getContentType(), attachmentPart.getInputStream());
            }

            Post updatedPost = messageBoardManager.updatePost(post);
            if(updatedPost == null) {
                throw new Exception("Edit failed! Post does not exist");
            }

            FrontendBoardManager.updatePost(request, updatedPost);
        } catch (Exception e) {
            request.getSession().setAttribute(DISPLAY_WARNING_POPUP, e.getMessage());
        } finally {
            request.getSession().removeAttribute("referredPost");
            response.sendRedirect(ROOT_PAGE);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirectPage = ROOT_PAGE;
        String postIDParam = request.getParameter("postID");
        try {
            if(postIDParam == null || postIDParam.isEmpty()) {
                throw new Exception("Edit failed! Invalid Post ID");
            }

            int postID = Integer.parseInt(postIDParam);
            int userID = (int) request.getSession().getAttribute("userID");
            if(!messageBoardManager.isPostOwner(userID, postID)) {
                throw new Exception("Edit failed! Permission denied");
            }

            Post post = messageBoardManager.getPost(postID);
            if(post == null) {
                throw new Exception("Edit failed! Post does not exist");
            }

            request.getSession().setAttribute("referredPost", post);
            redirectPage = UPDATE_POST_PAGE;
        } catch (Exception e) {
            request.getSession().setAttribute(DISPLAY_WARNING_POPUP, e.getMessage());
        } finally {
            response.sendRedirect(redirectPage);
        }
    }
}
