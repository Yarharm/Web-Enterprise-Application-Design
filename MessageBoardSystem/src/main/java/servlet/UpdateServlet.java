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

        Object postAttribute = request.getSession().getAttribute("referredPost");
        Post post = (Post) postAttribute;
        try {
            post.setPostTitle(title);
            post.setMessage(message);
        } catch (Exception e){
            e.printStackTrace();
            response.sendRedirect(MAIN_PAGE);
        }

        messageBoardManager.updatePost(post);

        request.getSession().removeAttribute("referredPost");

        response.sendRedirect(MAIN_PAGE);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("referredPost")!=null){
            response.sendRedirect(UPDATE_POST_PAGE);
        } else {
            response.sendRedirect(MAIN_PAGE);
        }

    }
}
