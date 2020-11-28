package preload_data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.User;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreloadUsers {
    public static void main(String[] args) throws IOException {
        User user1 = new User(1, "Bob", "testemail", "1234");
        User user2 = new User(2, "Max", "max@gmail.com", "qwerty");
        User user3 = new User(3, "Sara", "sara@yahoo.com", "somePassword");
        User user4 = new User(4, "Maya", "maya@someEmail.com", "password");

        List<User> defaultUsers = new ArrayList<>(Arrays.asList(user1, user2, user3, user4));

        try (Writer writer = new FileWriter("users.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(defaultUsers, writer);
        }
    }
}
