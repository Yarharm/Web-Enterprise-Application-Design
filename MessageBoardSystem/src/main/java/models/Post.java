package models;

import helpers.Utils;

import java.io.InputStream;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Post implements Serializable {
    private int userID;
    private String username;
    private int postID;
    private String postTitle;
    private String message;
    private long timestamp;
    private long lastModifiedTimestamp;
    private String attachment;
    private boolean containsAttachment;

    public Post() {}
    public Post(int userID, String username, String postTitle, String message, long timestamp, long lastModifiedTimestamp) {
        this.userID = userID;
        this.username = username;
        this.postTitle = postTitle;
        this.message = message;
        this.timestamp = timestamp;
        this.lastModifiedTimestamp = lastModifiedTimestamp;
        this.containsAttachment = false;
    }

    public Post(int postID, int userID, String username, String postTitle, String message, long timestamp, long lastModifiedTimestamp) {
        this(userID, username, postTitle, message, timestamp, lastModifiedTimestamp);
        this.postID = postID;
    }

    public void setAttachmentFromBinary(InputStream stream) {
        if(stream != null) {
            this.containsAttachment = true;
            this.attachment = Utils.inputStreamToImage(stream);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getLastModifiedTimestamp() {
        return lastModifiedTimestamp;
    }

    public void setLastModifiedTimestamp(long lastModifiedTimestamp) {
        this.lastModifiedTimestamp = lastModifiedTimestamp;
    }

    public void  searchHashtag(Post post) {
        String holder = post.getMessage();
        int holderID = post.getPostID();
        Pattern regex = Pattern.compile("#(\\w+)");
        Matcher match = regex.matcher(holder);
        while (match.find()) {
            Hashtag hash1 = new Hashtag(match.group(1),holderID);
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
