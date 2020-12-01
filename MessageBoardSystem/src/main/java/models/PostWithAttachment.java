package models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "post")
@XmlType(propOrder = { "fileName", "fileSize", "mediaType"})
public class PostWithAttachment extends Post {
    private String fileName;
    private long fileSize;
    private String mediaType;

    public PostWithAttachment(){}
    public PostWithAttachment(Post post, Attachment attachment) {
        super(post);
        this.fileName = attachment.getFileName();
        this.fileSize = attachment.getFileSize();
        this.mediaType = attachment.getMediaType();
    }

    @XmlElement(name = "fileName")
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @XmlElement(name = "fileSize")
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    @XmlElement(name = "mediaType")
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaType() {
        return mediaType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getFileName() {
        return fileName;
    }
}
