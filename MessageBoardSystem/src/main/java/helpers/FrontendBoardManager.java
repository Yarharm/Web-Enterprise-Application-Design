package helpers;

import models.Post;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentSkipListMap;

import static helpers.Constants.FRONTEND_MESSAGE_BOARD;

public class FrontendBoardManager {
    public static void appendMessageBoard(HttpServletRequest request, Post post) {
        ConcurrentSkipListMap<Long, Post> messageBoard = fetchMessageBoard(request);
        messageBoard.put(post.getTimestamp(), post);
    }

    private static ConcurrentSkipListMap<Long, Post> fetchMessageBoard(HttpServletRequest request) {
        ConcurrentSkipListMap<Long, Post> concurrentMessageBoard = (ConcurrentSkipListMap<Long, Post>) request.getServletContext().getAttribute(FRONTEND_MESSAGE_BOARD);
        if(concurrentMessageBoard == null) {
            concurrentMessageBoard = new ConcurrentSkipListMap<>();
            request.getServletContext().setAttribute(FRONTEND_MESSAGE_BOARD, concurrentMessageBoard);
        }

        return concurrentMessageBoard;
    }
}
