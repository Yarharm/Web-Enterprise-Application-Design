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
        String query = "INSERT INTO post (userID, username, postTitle, message, timestamp, dateString, lastModifiedTimestamp, postGroup) VALUES (?,?,?,?,?,?,?,?)";

        try {
            conn = DBConnector.getConnection();
            preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStmt.setInt(1, post.getUserID());
            preparedStmt.setString(2, post.getUsername());
            preparedStmt.setString(3, post.getPostTitle());
            preparedStmt.setString(4, post.getMessage());
            preparedStmt.setLong(5, post.getTimestamp());
            preparedStmt.setString(6, post.getDateString());
            preparedStmt.setLong(7, post.getTimestamp());
            preparedStmt.setString(8, post.getPostGroup());
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
        String query = "UPDATE post SET postTitle=?, message=?, postGroup=?  WHERE postID=?";

        try {
            conn = DBConnector.getConnection();
            preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString(1, post.getPostTitle());
            preparedStmt.setString(2, post.getMessage());
            preparedStmt.setString(3, post.getPostGroup());
            preparedStmt.setInt(4, post.getPostID());
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
        String query = "SELECT * FROM post WHERE postID=?";
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
        String query = "DELETE FROM post WHERE postID=?";
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
        String query = "SELECT * FROM post";

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


    public List<Integer> searchIDByDate(String date) {
        List<Integer> datePosts = new ArrayList<>();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM post WHERE dateString=?";

        try {
            conn = DBConnector.getConnection();
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, date);
            rs = preparedStmt.executeQuery();

            while(rs.next()) {
                datePosts.add(rs.getInt("postID"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnector.releaseConnection(conn, preparedStmt, rs);
        }
        return datePosts;
    }

    public List<Integer> searchIDByUser(String user) {
        List<Integer> userPosts = new ArrayList<>();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM post WHERE username=?";

        try {
            conn = DBConnector.getConnection();
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, user);
            rs = preparedStmt.executeQuery();

            while(rs.next()) {
                userPosts.add(rs.getInt("postID"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnector.releaseConnection(conn, preparedStmt, rs);
        }
        return userPosts;
    }

    public List<Post> getPaginatedPosts(int postCount) {
        List<Post> paginatedPosts = new ArrayList<>();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM post ORDER BY timestamp DESC LIMIT ?";

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
        String query = "UPDATE post SET lastModifiedTimestamp=? WHERE postID=?";

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
        String postGroup = rs.getString("postGroup");
        if (postTitle == null || message == null || postTitle.equals("") || message.equals("")){
            return null;
        } else {
            return new Post(postID, userID, username, postTitle, message, timestamp, lastModifiedTimestamp, postGroup);
        }
    }
}
