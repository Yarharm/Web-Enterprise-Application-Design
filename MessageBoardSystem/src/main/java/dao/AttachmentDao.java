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
        String query = "SELECT * FROM Attachments WHERE postID = ?";

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
        String query = "INSERT INTO Attachments (postID, fileName, fileSize, mediaType, attachment) VALUES (?,?,?,?,?)";

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
    public void update(int id, Attachment attachment) {

    }

    @Override
    public void delete(Attachment attachment) {

    }
}
