package preload_data;

import dao.UserDao;
import models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreloadUsers {
    public static void main(String[] args) {
        User user1 = new User(1, "Bob", "testemail", "1234");
        User user2 = new User(2, "Max", "max@gmail.com", "qwerty");
        User user3 = new User(3, "Sara", "sara@yahoo.com", "somePassword");
        User user4 = new User(4, "Maya", "maya@someEmail.com", "password");

        List<User> defaultUsers = new ArrayList<>(Arrays.asList(user1, user2, user3, user4));
        UserDao userDao = new UserDao();

        userDao.initDefaultUsers(defaultUsers);
    }
}
