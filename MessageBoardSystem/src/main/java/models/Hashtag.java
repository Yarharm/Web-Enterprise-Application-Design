package models;

import java.io.Serializable;

public class Hashtag implements Serializable {
    private String hashtag;
    private int postID;

    public Hashtag() {}
    public Hashtag(String hashtag, int postID) {
        this.hashtag = hashtag;
        this.postID = postID;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public int getpostID() {
        return postID;
    }

    public void setpostID(int postID) {
        this.postID = postID;
    }


    @Override
    public String toString() {
        return "Hashtag{" +
                "postID=" + hashtag +
                '}';
    }

}