package helpers;

import models.Post;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;

import static helpers.Constants.*;

public class FrontendBoardManager {
    public static void appendMessageBoard(HttpServletRequest request, Post post) {
        ConcurrentSkipListMap<Long, Post> messageBoard = fetchMessageBoard(request);
        messageBoard.put(post.getTimestamp(), post);
    }

    public static void refreshMessageBoard(HttpServletRequest request, List<Post> posts) {
        ConcurrentSkipListMap<Long, Post> messageBoard = fetchMessageBoard(request);
        Set<String> groupMembership = (Set<String>) request.getSession().getAttribute(GROUP_MEMBERSHIP_SESSION_ATTRIBUTE);
        messageBoard.clear();
        posts.forEach(post -> {
            if(groupMembership.contains(post.getPostGroup()) || post.getPostGroup().equals(PUBLIC_GROUP_MEMBERSHIP)) {
                messageBoard.put(post.getTimestamp(), post);
            }
        });
    }

    public static void updatePost(HttpServletRequest request, Post post) {
        ConcurrentSkipListMap<Long, Post> messageBoard = fetchMessageBoard(request);
        messageBoard.remove(post.getTimestamp());
        messageBoard.put(post.getTimestamp(), post);
    }

    private static ConcurrentSkipListMap<Long, Post> fetchMessageBoard(HttpServletRequest request) {
        ConcurrentSkipListMap<Long, Post> concurrentMessageBoard = (ConcurrentSkipListMap<Long, Post>) request.getSession().getAttribute(FRONTEND_MESSAGE_BOARD);
        if(concurrentMessageBoard == null) {
            concurrentMessageBoard = new ConcurrentSkipListMap<>(Collections.reverseOrder());
            request.getSession().setAttribute(FRONTEND_MESSAGE_BOARD, concurrentMessageBoard);
        }
        return concurrentMessageBoard;
    }
}
