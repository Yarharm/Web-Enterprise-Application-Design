package models;

import helpers.Utils;

import java.io.InputStream;
import java.io.Serializable;

public class Post implements Serializable {
    private int userID;
    private int postID;
    private String postTitle;
    private String message;
    private long timestamp;
    private String attachment;
    private boolean containsAttachment;

    public Post() {}
    public Post(int userID, String postTitle, String message, long timestamp) {
        this.userID = userID;
        this.postTitle = postTitle;
        this.message = message;
        this.timestamp = timestamp;
        this.containsAttachment = false;
    }

    public Post(int postID, int userID, String postTitle, String message, long timestamp) {
        this(userID, postTitle, message, timestamp);
        this.postID = postID;
    }

    public void setAttachmentFromBinary(InputStream stream) {
        if(stream != null) {
            this.containsAttachment = true;
            this.attachment = Utils.inputStreamToImage(stream);
        }
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public boolean isContainsAttachment() {
        return containsAttachment;
    }

    public void setContainsAttachment(boolean containsAttachment) {
        this.containsAttachment = containsAttachment;
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
