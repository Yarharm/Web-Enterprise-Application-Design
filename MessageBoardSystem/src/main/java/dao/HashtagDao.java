package dao;

import database.DBConnector;
import models.Hashtag;

import java.sql.*;

public class HashtagDao implements Dao<Hashtag> {

    public void save(Hashtag hashtag){
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        ResultSet rs = null;
        String query = "INSERT INTO hashtag (hashtag, postID) VALUES (?,?)";

        try {
            conn = DBConnector.getConnection();
            preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStmt.setString(1, hashtag.getHashtag());
            preparedStmt.setInt(2, hashtag.getpostID());
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
