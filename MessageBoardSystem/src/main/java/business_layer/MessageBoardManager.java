package business_layer;

import database.DBConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MessageBoardManager {
    public void postMessage(String userID, String title, String message){
        long currentTime = System.currentTimeMillis();
        String query = String.format("INSERT INTO posts (userID, postTitle, message, timestamp) VALUES ('%s', '%s', '%s', %s)", userID, title, message, currentTime);

        Connection c = DBConnector.getConnection();
        Statement stmt;
        try {
            stmt = c.prepareStatement(query);
            stmt.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
