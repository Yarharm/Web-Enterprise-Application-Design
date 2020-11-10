package business_layer;

import configuration.ConfigDriver;
import dao.AttachmentDao;
import dao.HashtagDao;
import dao.PostDao;
import dao.UserDao;
import models.Attachment;
import models.Hashtag;
import models.Post;
import models.User;

import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageBoardManager {
    private final UserDao userDao;
    private final PostDao postDao;
    private final AttachmentDao attachmentDao;
    private final HashtagDao hashtagDao;

    public MessageBoardManager() {
        this.userDao = new UserDao();
        this.postDao = new PostDao();
        this.attachmentDao = new AttachmentDao();
        this.hashtagDao = new HashtagDao();
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

    public Post postMessage(int userID, String username, String title, String message) {
        long currentTime = System.currentTimeMillis();

        Post post = new Post(userID, username, title, message, currentTime, currentTime);
        searchHashtag(post, post.getPostID());
        this.postDao.save(post);
        searchHashtag(post, post.getPostID());
        return post;
    }
    public void searchHashtag(Post post, int postID) {
        String holder = post.getMessage();
        Pattern regex = Pattern.compile("#(\\w+)");
        Matcher match = regex.matcher(holder);
        while (match.find()) {
            Hashtag hash1 = new Hashtag(match.group(1),postID);
            System.out.print(hash1);
            hashtagDao.save(hash1);
        }
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

    public List<Integer> getAllHashPosts(String hashtag) {
        List<Integer> posts = this.hashtagDao.searchHashTag(hashtag);
        return posts;
    }

    public List<Integer> getAllDatePosts(String sdate) {
        List<Integer> posts = this.postDao.searchDate(sdate);
        return posts;
    }
    public List<Integer> getAllUserPosts(String user) {
        List<Integer> posts = this.postDao.searchUser(user);
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
