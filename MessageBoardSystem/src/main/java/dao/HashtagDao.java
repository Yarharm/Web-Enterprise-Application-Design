package dao;

import database.DBConnector;
import models.Hashtag;
import models.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HashtagDao implements Dao<Hashtag> {

    public void save(Hashtag hashtag) {
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        ResultSet rs = null;
        String query = "INSERT INTO hashtag (hashtag, postID) VALUES (?,?)";

        try {
            conn = DBConnector.getConnection();
            preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString(1, hashtag.getHashtag());
            System.out.println(hashtag.getHashtag());
            preparedStmt.setInt(2, hashtag.getpostID());
            System.out.println(hashtag.getpostID());
            preparedStmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnector.releaseConnection(conn, preparedStmt, rs);
        }
    }

    @Override
    public void update(Hashtag hashtag) {

    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    private Hashtag constructHashtag(ResultSet rs) throws SQLException {
        int postID = rs.getInt("postID");
        String hashtag = rs.getString("hashtag");
        return new Hashtag(hashtag, postID);
    }



}