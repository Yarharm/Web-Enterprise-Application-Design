package business_layer;

import configuration.ConfigDriver;
import dao.AttachmentDao;
import dao.PostDao;
import models.Attachment;
import models.Post;
import dao.UserDao;
import models.User;

import java.io.InputStream;
import java.util.List;

public class MessageBoardManager {
    private final UserDao userDao;
    private final PostDao postDao;
    private final AttachmentDao attachmentDao;

    public MessageBoardManager() {
        this.userDao = new UserDao();
        this.postDao = new PostDao();
        this.attachmentDao = new AttachmentDao();
    }

    public Attachment getAttachment(int postID) {
        return this.attachmentDao.get(postID);
    }

    public Post removeAttachment(int postID) {
        boolean deleteStatus = this.attachmentDao.delete(postID);
        if(deleteStatus) {
            this.updateModificationTime(postID, System.currentTimeMillis());
        }
        return this.postDao.get(postID);
    }

    public void updateAttachment(Post post, String fileName, long fileSize, String mediaType, InputStream attachmentBinary) {
        Attachment attachment = new Attachment(post.getPostID(), fileName, fileSize, mediaType, attachmentBinary);
        this.attachmentDao.update(attachment);
        this.attachImageToPost(post);
    }

    public void saveAttachment(Post post, String fileName, long fileSize, String mediaType, InputStream attachmentBinary) {
        Attachment attachment = new Attachment(post.getPostID(), fileName, fileSize, mediaType, attachmentBinary);
        this.attachmentDao.save(attachment);
        this.attachImageToPost(post);
    }


    public void updateModificationTime(int postID, long modificationTimestamp) {
        this.postDao.updateModificationTime(postID, modificationTimestamp);
    }

    public boolean deletePost(int postID) {
        this.attachmentDao.delete(postID);
        return this.postDao.delete(postID);
    }

    public Post postMessage(int userID, String title, String message) {
        long currentTime = System.currentTimeMillis();

        Post post = new Post(userID, title, message, currentTime, currentTime);
        this.postDao.save(post);
        return post;
    }

    public Post getPost(int postID) {
        Post post = this.postDao.get(postID);
        this.attachImageToPost(post);
        return post;
    }

    public List<Post> getAllPosts() {
        List<Post> posts = this.postDao.getAll();
        posts.forEach(this::attachImageToPost);
        return posts;
    }

    public List<Post> getMostRecentPosts() {
        int postCount = ConfigDriver.getPaginationSize();
        List<Post> paginatedPosts = this.postDao.getPaginatedPosts(postCount);
        paginatedPosts.forEach(this::attachImageToPost);
        return paginatedPosts;
    }
    

    public Post updatePost(Post post){
        this.postDao.update(post);
        this.updateModificationTime(post.getPostID(), System.currentTimeMillis());
        return this.getPost(post.getPostID());
    }

    public User loginUser(String email, String password) {
        return this.userDao.get(email, password);
    }

    public boolean isPostOwner(int userID, int postID) {
        Post post = this.getPost(postID);
        return post != null && userID == post.getUserID();
    }

    private void attachImageToPost(Post post) {
        if(post != null) {
            post.setAttachmentFromBinary(this.attachmentDao.getAttachmentBinary(post.getPostID()));
        }
    }
}
