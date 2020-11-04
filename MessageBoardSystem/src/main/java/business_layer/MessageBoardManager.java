package business_layer;

import dao.PostDao;
import database.DBConnector;
import models.Post;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MessageBoardManager {
    public void postMessage(int userID, String title, String message){
        PostDao pd = new PostDao();
        long currentTime = System.currentTimeMillis();

        Post post = new Post(userID, title, message, currentTime);
        pd.save(post);
    }
}
