package servlet;

import business_layer.MessageBoardManager;
import models.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static helpers.Constants.*;

@WebServlet(name = "UpdateServlet")
public class UpdateServlet extends HttpServlet {
    MessageBoardManager messageBoardManager = new MessageBoardManager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String message = request.getParameter("message");

        Object post = request.getSession().getAttribute("referredPost");
        Post p = (Post) post;

        p.setPostTitle(title);
        p.setMessage(message);

        messageBoardManager.updatePost(p.getPostID(), (Post) post);

        response.sendRedirect(MAIN_PAGE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("referredTitle");
        String message = request.getParameter("referredMessage");
        int postID = Integer.parseInt(request.getParameter("referredPostID"));

        request.getServletContext().setAttribute("referredTitle", title);
        request.getServletContext().setAttribute("referredMessage", message);
        request.getServletContext().setAttribute("referredPostID", postID);

        response.sendRedirect(UPDATE_POST_PAGE);
    }
}
