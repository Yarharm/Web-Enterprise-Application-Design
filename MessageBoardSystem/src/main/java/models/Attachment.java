package models;

import java.io.InputStream;
import java.io.Serializable;

public class Attachment implements Serializable {
    private int postID;
    private String fileName;
    private long fileSize;
    private String mediaType;
    private InputStream attachmentBinary;

    public Attachment() {}
    public Attachment(int postID, String fileName, long fileSize, String mediaType, InputStream attachmentBinary) {
        this.postID = postID;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.mediaType = mediaType;
        this.attachmentBinary = attachmentBinary;
    }

    public InputStream getAttachmentBinary() {
        return attachmentBinary;
    }

    public void setAttachmentBinary(InputStream attachmentBinary) {
        this.attachmentBinary = attachmentBinary;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }


    @Override
    public String toString() {
        return "Attachment{" +
                "postID=" + postID +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                '}';
    }
}
