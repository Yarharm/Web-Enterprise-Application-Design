package dao;

import database.DBConnector;
import models.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM posts";

        try {
            conn = DBConnector.getConnection();
            preparedStmt = conn.prepareStatement(query);

            rs = preparedStmt.executeQuery();

            while(rs.next()) {
                posts.add(this.constructPost(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnector.releaseConnection(conn, preparedStmt, rs);
        }
        return posts;
    }

    public List<Post> getPaginatedPosts(int postCount) {
        List<Post> paginatedPosts = new ArrayList<>();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM posts ORDER BY timestamp DESC LIMIT ?";

        try {
            conn = DBConnector.getConnection();
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, postCount);

            rs = preparedStmt.executeQuery();

            while(rs.next()) {
                paginatedPosts.add(this.constructPost(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnector.releaseConnection(conn, preparedStmt, rs);
        }
        return paginatedPosts;
    }

    private Post constructPost(ResultSet rs) throws SQLException {
        int postID = rs.getInt("postID");
        int userID = rs.getInt("userID");
        String postTitle = rs.getString("postTitle");
        String message = rs.getString("message");
        long timestamp = rs.getLong("timestamp");
        return new Post(postID, userID, postTitle, message, timestamp);
    }
}
