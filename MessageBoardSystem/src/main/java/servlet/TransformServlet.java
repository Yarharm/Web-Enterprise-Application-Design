package servlet;

import business_layer.MessageBoardManager;
import helpers.XMLSerializer;
import models.Attachment;
import models.Post;
import models.PostWithAttachment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static helpers.Constants.DISPLAY_WARNING_POPUP;
import static helpers.Constants.ROOT_PAGE;

@WebServlet(name = "TransformServlet")
public class TransformServlet extends HttpServlet {
    MessageBoardManager boardManager = new MessageBoardManager();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postID = request.getParameter("postID");
        try {
            if(postID == null || postID.isEmpty()) {
                throw new Exception("View as XML failed! Missing postID");
            }
            Post post = boardManager.getPost(Integer.parseInt(postID));
            if(post.isContainsAttachment()) {
                Attachment attachment = boardManager.getAttachment(post.getPostID());
                post = new PostWithAttachment(post, attachment);
            }
            request.getSession().setAttribute("xmlpost", XMLSerializer.serialize(post));
            String xsl = xsl = getServletConfig().getServletContext().getRealPath("WEB-INF/post.xsl");
            request.getSession().setAttribute("xslt", new String(Files.readAllBytes(Paths.get(xsl))));
            getServletContext().getRequestDispatcher("/transformPost.jsp").forward(request, response);
        }  catch (Exception e) {
            request.getSession().setAttribute(DISPLAY_WARNING_POPUP, e.getMessage());
            response.sendRedirect(ROOT_PAGE);
        }
    }
}
