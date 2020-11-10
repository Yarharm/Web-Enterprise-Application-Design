package dao;

import database.DBConnector;
import models.Hashtag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            preparedStmt.setInt(2, hashtag.getpostID());
            preparedStmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnector.releaseConnection(conn, preparedStmt, rs);
        }
    }

    public List<Integer> searchHashTag(String hash) {
        List<Integer> hashes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM hashtag WHERE hashtag.hastag=?";

        try {
            conn = DBConnector.getConnection();
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, hash);
            rs = preparedStmt.executeQuery();
            while(rs.next()) {
                hashes.add(rs.getInt("postID"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnector.releaseConnection(conn, preparedStmt, rs);
        }
        return hashes;
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