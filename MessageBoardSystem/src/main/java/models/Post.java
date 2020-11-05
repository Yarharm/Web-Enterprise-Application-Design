package models;

import java.io.Serializable;

public class Post implements Serializable {
    private int userID;
    private int postID;
    private String postTitle;
    private String message;
    private long timestamp;

    public Post() {}
    public Post(int userID, String postTitle, String message, long timestamp) {
        this.userID = userID;
        this.postTitle = postTitle;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getUserID() {
        return userID;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getPostID() { return postID; }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setPostID(int postID) { this.postID = postID; }

    @Override
    public String toString() {
        return "Post{" +
                "userID=" + userID +
                ", postID=" + postID +
                ", postTitle='" + postTitle + '\'' +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
