package models;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@XmlRootElement(name = "post")
@XmlType(propOrder = { "username", "postTitle", "message", "dateString", "postGroup", "containsAttachment"})
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
    private String dateString;
    private String postGroup;
    private final DateFormat simple = new SimpleDateFormat("yyyy-MM-dd");


    public Post() {}

    public Post(Post post) {
        this(post.getPostID(), post.getUserID(), post.getUsername(), post.getPostTitle(), post.getMessage(), post.getTimestamp(), post.getLastModifiedTimestamp(), post.getPostGroup());
        this.containsAttachment = post.isContainsAttachment();
        this.attachment = post.getAttachment();
        this.dateString = post.getDateString();
    }

    public Post(int userID, String username, String postTitle, String message, long timestamp, long lastModifiedTimestamp, String postGroup) {
        this.userID = userID;
        this.username = username;
        this.postTitle = postTitle;
        this.message = message;
        this.timestamp = timestamp;
        this.dateString = simple.format(timestamp);
        this.lastModifiedTimestamp = lastModifiedTimestamp;
        this.containsAttachment = false;
        this.postGroup = postGroup;
    }

    public Post(int postID, int userID, String username, String postTitle, String message, long timestamp, long lastModifiedTimestamp, String postGroup) {
        this(userID, username, postTitle, message, timestamp, lastModifiedTimestamp, postGroup);
        this.postID = postID;
    }

    @XmlElement(name = "postGroup")
    public void setPostGroup(String postGroup) {
        this.postGroup = postGroup;
    }

    @XmlElement(name = "date")
    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    @XmlElement(name = "username")
    public void setUsername(String username) {
        this.username = username;
    }

    @XmlElement(name = "attachment")
    public void setContainsAttachment(boolean containsAttachment) {
        this.containsAttachment = containsAttachment;
    }

    @XmlElement(name = "title")
    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    @XmlElement(name = "message")
    public void setMessage(String message) {
        this.message = message;
    }

    @XmlTransient
    public void setUserID(int userID) {
        this.userID = userID;
    }

    @XmlTransient
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        this.dateString = simple.format(timestamp);
    }

    @XmlTransient
    public void setLastModifiedTimestamp(long lastModifiedTimestamp) {
        this.lastModifiedTimestamp = lastModifiedTimestamp;
    }

    @XmlTransient
    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    @XmlTransient
    public void setPostID(int postID) { this.postID = postID; }

    @XmlTransient
    public void setAttachmentFromObj(Attachment attachmentObj) {
        if(attachmentObj != null) {
            this.containsAttachment = true;
            this.attachment = attachmentObj.getFileName();
        }
    }

    public String getDateString() {
        return dateString;
    }

    public String getAttachment() {
        return attachment;
    }

    public boolean isContainsAttachment() {
        return containsAttachment;
    }

    public String getPostGroup() {
        return postGroup;
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

    public long getLastModifiedTimestamp() {
        return lastModifiedTimestamp;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "Post{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", postID=" + postID +
                ", postTitle='" + postTitle + '\'' +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", lastModifiedTimestamp=" + lastModifiedTimestamp +
                ", attachment='" + attachment + '\'' +
                ", containsAttachment=" + containsAttachment +
                ", dateString='" + dateString + '\'' +
                ", postGroup='" + postGroup + '\'' +
                ", simple=" + simple +
                '}';
    }
}