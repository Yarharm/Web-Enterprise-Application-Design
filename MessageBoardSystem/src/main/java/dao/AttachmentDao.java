package dao;

import database.DBConnector;
import models.Attachment;

import java.io.InputStream;
import java.sql.*;

public class AttachmentDao implements Dao<Attachment> {
    public Attachment get(int postID) {
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM attachment WHERE postID = ?";

        try {
            conn = DBConnector.getConnection();
            preparedStmt = conn.prepareStatement(query);

            preparedStmt.setInt(1, postID);

            rs = preparedStmt.executeQuery();
            if(rs.next()) {
                String fileName = rs.getString("fileName");
                long fileSize = rs.getLong("fileSize");
                String mediaType = rs.getString("mediaType");
                Blob attachmentBlob = rs.getBlob("attachment");
                return new Attachment(postID, fileName, fileSize, mediaType, attachmentBlob.getBinaryStream());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnector.releaseConnection(conn, preparedStmt, rs);
        }
        return null;
    }

    public InputStream getAttachmentBinary(int postID) {
        Attachment attachment = this.get(postID);
        return attachment != null ? attachment.getAttachmentBinary() : null;
    }

    @Override
    public void save(Attachment attachment) {
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        String query = "INSERT INTO attachment (postID, fileName, fileSize, mediaType, attachment) VALUES (?,?,?,?,?)";

        try {
            conn = DBConnector.getConnection();
            preparedStmt = conn.prepareStatement(query);

            preparedStmt.setInt(1, attachment.getPostID());
            preparedStmt.setString(2, attachment.getFileName());
            preparedStmt.setLong(3, attachment.getFileSize());
            preparedStmt.setString(4, attachment.getMediaType());
            preparedStmt.setBinaryStream(5, attachment.getAttachmentBinary());

            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnector.releaseConnection(conn, preparedStmt);
        }
    }

    @Override
    public void update(Attachment attachment) {
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        String query = "UPDATE attachment SET fileName=?,fileSize=?,mediaType=?,attachment=? WHERE postID=?";

        try {
            conn = DBConnector.getConnection();
            preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString(1, attachment.getFileName());
            preparedStmt.setLong(2, attachment.getFileSize());
            preparedStmt.setString(3, attachment.getMediaType());
            preparedStmt.setBinaryStream(4, attachment.getAttachmentBinary());
            preparedStmt.setInt(5, attachment.getPostID());

            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnector.releaseConnection(conn, preparedStmt);
        }
    }

    @Override
    public boolean delete(int postID) {
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        String query = "DELETE FROM attachment WHERE postID=?";
        boolean deleteStatus = false;

        try {
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
}
