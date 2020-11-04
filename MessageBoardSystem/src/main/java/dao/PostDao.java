package dao;

import database.DBConnector;
import models.Post;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PostDao implements Dao<Post> {

    public void save(Post post){
        Connection c = DBConnector.getConnection();
        Statement stmt = null;
        String query = String.format("INSERT INTO posts (userID, postTitle, message, timestamp) VALUES ('%s', '%s', '%s', %s)", post.getUserID(), post.getPostTitle(), post.getMessage(), post.getTimestamp());

        try {
            stmt = c.prepareStatement(query);
            stmt.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnector.releaseConnection(c, stmt);
        }
    }

    @Override
    public void update(int id, Post post) {

    }

    @Override
    public void delete(Post post) {

    }

    @Override
    public Post get(int id) {
        return null;
    }
}
