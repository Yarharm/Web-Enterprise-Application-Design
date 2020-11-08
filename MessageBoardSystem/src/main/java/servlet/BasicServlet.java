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
import java.util.List;

import static helpers.Constants.ROOT_PAGE;

@WebServlet(name = "BasicServlet")
@MultipartConfig
public class BasicServlet extends HttpServlet {
    MessageBoardManager boardManager = new MessageBoardManager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        String title = request.getParameter("title");
        Part attachmentPart = request.getPart("attachment");
        int userID = (int) request.getSession().getAttribute("userID");

        if(message != null && title != null && !message.isEmpty() && !title.isEmpty()) {
            String escapedTitle = StringEscapeUtils.escapeXml(title);
            String escapedMessage = StringEscapeUtils.escapeXml(message);
            Post post = boardManager.postMessage(userID, escapedTitle, escapedMessage);

            if(attachmentPart.getSize() > 0) {
                this.boardManager.saveAttachment(post, attachmentPart.getSubmittedFileName(), attachmentPart.getSize(),
                        attachmentPart.getContentType(), attachmentPart.getInputStream());

            }
            FrontendBoardManager.appendMessageBoard(request, post);
        }
        response.sendRedirect(ROOT_PAGE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Post> posts = this.boardManager.getAllPosts();
        FrontendBoardManager.refreshMessageBoard(request, posts);
        request.getSession().setAttribute("FirstLogin", "FirstLogin");
        response.sendRedirect(ROOT_PAGE);
    }
}
