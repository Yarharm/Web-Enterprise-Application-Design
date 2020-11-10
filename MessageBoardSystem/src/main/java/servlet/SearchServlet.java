package servlet;

import business_layer.MessageBoardManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static helpers.Constants.DISPLAY_WARNING_POPUP;
import static helpers.Constants.ROOT_PAGE;

@WebServlet(name = "SearchServlet")
@MultipartConfig
public class SearchServlet extends HttpServlet {
    MessageBoardManager boardManager = new MessageBoardManager();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirectPage = ROOT_PAGE;
        String postIDParam = request.getParameter("postID");
        try {
            String hashtag = request.getParameter("hash");
            String date = request.getParameter("startTime");
            String author = request.getParameter("author");
            Set<Integer> set = new HashSet<>();

            try {
                if (!hashtag.isEmpty()) {
                    String[] hashholder=hashtag.split("-");
                    for(int i =0; i<hashholder.length; i++) {
                        set.addAll(boardManager.getAllHashPosts(hashholder[i]));
                    }

                }
                if (!date.isEmpty()) {
                        set.addAll(boardManager.getAllHashPosts(date));


                }
                if (!author.isEmpty()) {

                        set.addAll(boardManager.getAllHashPosts(author));
                }

                if (!date.isEmpty() && !hashtag.isEmpty() && !author.isEmpty()) {
                    //esearch for all
                }


            } catch (Exception e) {
                request.getSession().setAttribute(DISPLAY_WARNING_POPUP, e.getMessage());
            } finally {
                response.sendRedirect(redirectPage);
            }
        } finally {
            response.sendRedirect(redirectPage);
        }
    }
}
