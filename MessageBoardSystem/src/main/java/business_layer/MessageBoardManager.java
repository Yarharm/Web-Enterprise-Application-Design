package business_layer;

import dao.AttachmentDao;
import dao.PostDao;
import models.Attachment;
import models.Post;
import dao.UserDao;
import models.User;

import java.io.InputStream;

public class MessageBoardManager {
    private final UserDao userDao;
    private final PostDao postDao;
    private final AttachmentDao attachmentDao;

    public MessageBoardManager() {
        this.userDao = new UserDao();
        this.postDao = new PostDao();
        this.attachmentDao = new AttachmentDao();
    }

    public void saveAttachment(Post post, String fileName, long fileSize, String mediaType, InputStream attachmentBinary) {
        Attachment attachment = new Attachment(post.getPostID(), fileName, fileSize, mediaType, attachmentBinary);
        this.attachmentDao.save(attachment);
        post.setAttachmentFromBinary(this.attachmentDao.getAttachmentBinary(post.getPostID()));
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
