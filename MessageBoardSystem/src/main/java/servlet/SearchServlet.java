package servlet;

import business_layer.MessageBoardManager;
import helpers.FrontendBoardManager;
import models.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static helpers.Constants.*;

@WebServlet(name = "SearchServlet")
@MultipartConfig
public class SearchServlet extends HttpServlet {
    MessageBoardManager boardManager = new MessageBoardManager();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirectPage = ROOT_PAGE;
        String postIDParam = request.getParameter("postID");
            String hashtag = request.getParameter("hash");
            String date = request.getParameter("startTime");
            String author = request.getParameter("author");
            Set<Integer> set = new HashSet<>();

            try {
                if (!hashtag.isEmpty()) {
                    String[] hashholder = hashtag.split("-");
                    for (int i = 0; i < hashholder.length; i++) {
                        set.addAll(boardManager.getAllHashPosts(hashholder[i]));
                    }


                }
                if (!date.isEmpty()) {
                        set.addAll(boardManager.getAllDatePosts(date));


                }
                if (!author.isEmpty()) {

                        set.addAll(boardManager.getAllUserPosts(author));
                        System.out.println(set.toString());
                }
                List<Post> postList = new ArrayList<Post>();

                for (Integer postID : set) {
                    Post postHolder = boardManager.getPost(postID);

                    if(postHolder != null){
                        postList.add(postHolder);
                    }

                }
                FrontendBoardManager.refreshMessageBoard(request, postList);

            } catch (Exception e) {
                request.getSession().setAttribute(DISPLAY_WARNING_POPUP, e.getMessage());
            } finally {
                response.sendRedirect(redirectPage);
            }

    }
}
