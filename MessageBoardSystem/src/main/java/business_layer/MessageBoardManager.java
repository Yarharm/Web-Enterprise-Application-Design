package business_layer;

import dao.PostDao;
import models.Post;
import dao.UserDao;
import models.User;

public class MessageBoardManager {
    private final UserDao userDao;
    private final PostDao postDao;

    public MessageBoardManager() {
        userDao = new UserDao();
        postDao = new PostDao();
    }

    public Post postMessage(int userID, String title, String message) {
        long currentTime = System.currentTimeMillis();

        Post post = new Post(userID, title, message, currentTime);
        this.postDao.save(post);
        return post;
    }

    public User loginUser(String email, String password) {
        return this.userDao.get(email, password);
    }
}
