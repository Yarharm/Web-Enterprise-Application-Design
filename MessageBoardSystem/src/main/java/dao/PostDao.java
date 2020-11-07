package dao;

import database.DBConnector;
import models.Post;

import java.sql.*;

public class PostDao implements Dao<Post> {

    public void save(Post post){
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        ResultSet rs = null;
        String query = "INSERT INTO posts (userID, postTitle, message, timestamp) VALUES (?,?,?,?)";

        try {
            conn = DBConnector.getConnection();
            preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStmt.setInt(1, post.getUserID());
            preparedStmt.setString(2, post.getPostTitle());
            preparedStmt.setString(3, post.getMessage());
            preparedStmt.setLong(4, post.getTimestamp());
            preparedStmt.executeUpdate();

            rs = preparedStmt.getGeneratedKeys();
            if(rs.next()) {
                post.setPostID(rs.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnector.releaseConnection(conn, preparedStmt, rs);
        }
    }

    @Override
    public void update(int id, Post post) {

    }

    @Override
    public void delete(Post post) {

    }

    public Post get(int id) {
        return null;
    }
}
