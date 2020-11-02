package dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import database.DBConnector;
import models.User;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDao implements Dao<User> {
    private final static Gson gson = new Gson();

    public void save(User t) {}

    public void update(int id, User t) {}

    public void delete(User t) {}

    public User get(int userID) {
        return null;
    }

    public void initDefaultUsers(List<User> defaultUsers) {
        String usersJSON = gson.toJson(defaultUsers);
        InputStream usersBinary = new ByteArrayInputStream(usersJSON.getBytes(StandardCharsets.UTF_8));

        Connection conn = null;
        PreparedStatement preparedStmt = null;
        try {
            String query = "INSERT INTO Users (UserFile) VALUES (?)";
            conn = DBConnector.getConnection();
            preparedStmt = conn.prepareStatement(query);

            preparedStmt.setBinaryStream(1, usersBinary);

            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.releaseConnection(conn, preparedStmt);
        }
    }

    private List<User> getUserFile() {
        List<User> users = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM Users";
            conn = DBConnector.getConnection();
            stmt = conn.createStatement();

            rs = stmt.executeQuery(query);
            rs.next();

            InputStream binaryUsers = rs.getBlob(1).getBinaryStream();
            Reader reader = new InputStreamReader(binaryUsers);

            Type collectionType = new TypeToken<Collection<User>>(){}.getType();
            users.addAll(gson.fromJson(reader, collectionType));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnector.releaseConnection(conn, stmt, rs);
        }
        return users;
    }
}
