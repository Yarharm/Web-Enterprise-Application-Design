package servlet;

import business_layer.MessageBoardManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static helpers.Constants.DISPLAY_WARNING_POPUP;
import static helpers.Constants.ROOT_PAGE;

@WebServlet(name = "SearchServlet")
public class SearchServlet extends HttpServlet {
    MessageBoardManager boardManager = new MessageBoardManager();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hashtag = request.getParameter("hash");
        String date = request.getParameter("startTime");
        String author = request.getParameter("author");

        try {
            if(!hashtag.isEmpty() && author.isEmpty() && date.isEmpty()) {
                boardManager.getAllHashPosts(hashtag);
            }

            if(!author.isEmpty() && hashtag.isEmpty() && date.isEmpty()) {
                //search author
            }

            if(author.isEmpty() && hashtag.isEmpty() && !date.isEmpty()) {
                //search date
            }

            if(!date.isEmpty() && !hashtag.isEmpty() && author.isEmpty()) {
               //search for date and hastag
            }
            if(!date.isEmpty() && hashtag.isEmpty() && !author.isEmpty()) {
                //search for date and author
            }
            if(date.isEmpty() && !hashtag.isEmpty() && !author.isEmpty()) {
                //search for hastag and author
            }
            if(!date.isEmpty() && !hashtag.isEmpty() && !author.isEmpty()) {
                //esearch for all
            }



        } catch (Exception e) {
            request.getSession().setAttribute(DISPLAY_WARNING_POPUP, e.getMessage());
        } finally {
            response.sendRedirect(ROOT_PAGE);
        }
    }
}
