package dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import helpers.Utils;
import models.User;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDao implements Dao<User> {
    private final static String USER_FILE_NAME = "users.json";
    private final static Gson gson = new Gson();

    @Override
    public void save(User t) {}

    @Override
    public void update(User t) {}

    @Override
    public boolean delete(int id) {return false;}

    public User get(String email, String password) {
        return this.getUsers().stream()
                .filter(user -> user.userExists(email, password))
                .findAny()
                .orElse(null);
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            String userFilePath = Utils.buildTargetFilePath(USER_FILE_NAME);
            JsonReader reader = new JsonReader(new FileReader(userFilePath));

            Type collectionType = new TypeToken<Collection<User>>(){}.getType();
            users.addAll(gson.fromJson(reader, collectionType));
        } catch(IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}
