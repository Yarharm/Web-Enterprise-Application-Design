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
        String query = "INSERT INTO posts (userID, username, postTitle, message, timestamp, lastModifiedTimestamp) VALUES (?,?,?,?,?,?)";

        try {
            conn = DBConnector.getConnection();
            preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStmt.setInt(1, post.getUserID());
            preparedStmt.setString(2, post.getUsername());
            preparedStmt.setString(3, post.getPostTitle());
            preparedStmt.setString(4, post.getMessage());
            preparedStmt.setLong(5, post.getTimestamp());
            preparedStmt.setLong(6, post.getTimestamp());
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

    public void update(Post post) {
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        String query = "UPDATE posts SET postTitle=?, message=? WHERE postID=?";

        try {
            conn = DBConnector.getConnection();
            preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString(1, post.getPostTitle());
            preparedStmt.setString(2, post.getMessage());
            preparedStmt.setInt(3, post.getPostID());
            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnector.releaseConnection(conn, preparedStmt);
        }
    }

    public Post get(int postID) {
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM posts WHERE postID=?";
        Post post = null;

        try {
            conn = DBConnector.getConnection();
            preparedStmt = conn.prepareStatement(query);

            preparedStmt.setInt(1, postID);

            rs = preparedStmt.executeQuery();
            if(rs.next()) {
                post = this.constructPost(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnector.releaseConnection(conn, preparedStmt, rs);
        }
        return post;

    }

    @Override
    public boolean delete(int postID) {
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        String query = "DELETE FROM posts WHERE postID=?";
        boolean deleteStatus = false;

        try{
            conn = DBConnector.getConnection();
            preparedStmt = conn.prepareStatement(query);

            preparedStmt.setInt(1, postID);

            int rowsAffected = preparedStmt.executeUpdate();
            deleteStatus = rowsAffected > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnector.releaseConnection(conn, preparedStmt);
        }
        return deleteStatus;
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
                if (this.constructPost(rs)!=null) {
                    posts.add(this.constructPost(rs));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnector.releaseConnection(conn, preparedStmt, rs);
        }
        return posts;
    }

    public List<Post> searchHashTag(String hash) {
        List<Post> hashes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM posts INNER JOIN hashtag ON posts.id=hashtag.id WHERE hashtag.hashtag=hash";

        try {
            conn = DBConnector.getConnection();
            preparedStmt = conn.prepareStatement(query);

            rs = preparedStmt.executeQuery();

            while(rs.next()) {
                hashes.add(this.constructPost(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnector.releaseConnection(conn, preparedStmt, rs);
        }
        return hashes;
    }

    public List<Post> searchTime(Date time) {
        List<Post> hashes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM posts WHERE posts.timestamp=time";

        try {
            conn = DBConnector.getConnection();
            preparedStmt = conn.prepareStatement(query);

            rs = preparedStmt.executeQuery();

            while(rs.next()) {
                hashes.add(this.constructPost(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnector.releaseConnection(conn, preparedStmt, rs);
        }
        return hashes;
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
                if (this.constructPost(rs)!=null) {
                    paginatedPosts.add(this.constructPost(rs));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnector.releaseConnection(conn, preparedStmt, rs);
        }
        return paginatedPosts;
    }

    public void updateModificationTime(int postID, long modificationTimestamp) {
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        String query = "UPDATE posts SET lastModifiedTimestamp=? WHERE postID=?";

        try {
            conn = DBConnector.getConnection();
            preparedStmt = conn.prepareStatement(query);

            preparedStmt.setLong(1, modificationTimestamp);
            preparedStmt.setInt(2, postID);

            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnector.releaseConnection(conn, preparedStmt);
        }
    }

    private Post constructPost(ResultSet rs) throws SQLException {
        int postID = rs.getInt("postID");
        int userID = rs.getInt("userID");
        String username = rs.getString("username");
        String postTitle = rs.getString("postTitle");
        String message = rs.getString("message");
        long timestamp = rs.getLong("timestamp");
        long lastModifiedTimestamp = rs.getLong("lastModifiedTimestamp");
        if (postTitle == null || message == null || postTitle.equals("") || message.equals("")){
            return null;
        } else {
            return new Post(postID, userID, username, postTitle, message, timestamp, lastModifiedTimestamp);
        }
    }
}
