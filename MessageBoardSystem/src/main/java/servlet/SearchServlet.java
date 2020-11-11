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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static helpers.Constants.DISPLAY_WARNING_POPUP;
import static helpers.Constants.ROOT_PAGE;

@WebServlet(name = "SearchServlet")
@MultipartConfig
public class SearchServlet extends HttpServlet {
    MessageBoardManager boardManager = new MessageBoardManager();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hashtag = request.getParameter("hash");
        String date = request.getParameter("startTime");
        String author = request.getParameter("author");
        List<Post> filteredPosts = boardManager.getAllPosts();

        try {
            if (!hashtag.isEmpty()) {
                String[] hashholder = hashtag.split("-");
                Set<Integer> set = new HashSet<>();
                for (int i = 0; i < hashholder.length; i++) {
                    set.addAll(boardManager.getAllHashPosts(hashholder[i]));
                }
                filteredPosts = filteredPosts.stream().filter(post -> set.contains(post.getPostID())).collect(Collectors.toList());
            }
            if (!date.isEmpty()) {
                Set<Integer> set = new HashSet<>(boardManager.getAllDatePosts(date));
                filteredPosts = filteredPosts.stream().filter(post -> set.contains(post.getPostID())).collect(Collectors.toList());
            }
            if (!author.isEmpty()) {
                Set<Integer> set = new HashSet<>(boardManager.getAllUserPosts(author));
                filteredPosts = filteredPosts.stream().filter(post -> set.contains(post.getPostID())).collect(Collectors.toList());

            }

            FrontendBoardManager.refreshMessageBoard(request, filteredPosts);
        } catch (Exception e) {
            request.getSession().setAttribute(DISPLAY_WARNING_POPUP, e.getMessage());
        } finally {
            response.sendRedirect(ROOT_PAGE);
        }
    }
}
