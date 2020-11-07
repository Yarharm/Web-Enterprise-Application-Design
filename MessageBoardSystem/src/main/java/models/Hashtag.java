package models;

import java.io.Serializable;

public class Hashtag implements Serializable {
    private String hashtag;
    private int postIDlist;

    public Hashtag() {}
    public Hashtag(String hashtag, int postID) {
        this.hashtag = hashtag;
        this.postIDlist = postID;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }


    @Override
    public String toString() {
        return "Hashtag{" +
                "postID=" + hashtag +
                '}';
    }

}