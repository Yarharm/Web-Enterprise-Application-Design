package business_layer;
import com.google.gson.stream.JsonReader;
import helpers.Utils;
import models.User;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class UserManager {
    private final static String USER_FILE_NAME = "users.json";
    private final static String USER_GROUP_ADMIN = "admins";
    private final List<User> users;
    private final Map<String, List<String>> groups;
    private final Map<String, List<String>> memberships;
    private boolean finishedLoadingData;

    public UserManager() {
        users = new ArrayList<>();
        groups = new HashMap<>();
        memberships = new HashMap<>();
        finishedLoadingData = false;
    }

    public Set<String> loadGroupMembership(String username) {
        return this.loadGroupMembership(username, USER_FILE_NAME);
    }

    public Set<String> loadGroupMembership(String username, String userConfig) {
        fetchUserInformation(userConfig);
        return null;
    }

    public User getUser(String email, String password) {
        fetchUserInformation(USER_FILE_NAME);
        return getUsers().stream()
                .filter(user -> user.userExists(email, password))
                .findAny()
                .orElse(null);
    }

    private void fetchUserInformation(String userConfig) {
        if(!finishedLoadingData) {
            try {
                loadUserFile(userConfig);
                finishedLoadingData = true;
            } catch (Exception e) {
                System.err.println("Could not allocate user file");
                System.exit(1);
            }
        }
    }

    private void loadUserFile(String userConfig) throws IOException {
        String configPath = Utils.buildTargetFilePath(userConfig);

        try (JsonReader reader = new JsonReader(new FileReader(configPath))) {
            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                switch (name) {
                    case "users":
                        readUsers(reader);
                        break;
                    case "groups":
                        readGroups(reader);
                        break;
                    case "memberships":
                        readMemberships(reader);
                        break;
                    default:
                        throw new IOException();
                }
            }
            reader.endObject();
        }
    }

    private void readMemberships(JsonReader reader) throws IOException {
        reader.beginArray();
        while(reader.hasNext()) {
            String username = "";

            reader.beginObject();
            while (reader.hasNext()) {
                String tokenName = reader.nextName();
                if (tokenName.equals("username")) {
                    username = reader.nextString();
                } else if(tokenName.equals("userGroups")) {
                    memberships.computeIfAbsent(username, k -> new ArrayList<>());
                    reader.beginArray();
                    while(reader.hasNext()) {
                        memberships.get(username).add(reader.nextString());
                    }
                    reader.endArray();
                }
            }
            reader.endObject();
        }
        reader.endArray();
    }

    private void readGroups(JsonReader reader) throws IOException {
        reader.beginArray();
        while(reader.hasNext()) {
            String name = "";
            String parent = "";

            reader.beginObject();
            while (reader.hasNext()) {
                String tokenName = reader.nextName();
                if (tokenName.equals("name")) {
                    name = reader.nextString();
                } else if(tokenName.equals("parent")) {
                    parent = reader.nextString();
                }
            }
            reader.endObject();

            parent = parent.isEmpty() && !name.equals(USER_GROUP_ADMIN) ? USER_GROUP_ADMIN : parent;
            groups.computeIfAbsent(parent, k -> new ArrayList<>());
            groups.computeIfAbsent(name, k -> new ArrayList<>());
            groups.get(parent).add(name);
        }
        reader.endArray();
    }

    private void readUsers(JsonReader reader) throws IOException {
        reader.beginArray();
        while(reader.hasNext()) {
            int userID = -1;
            String username = "";
            String email = "";
            String password = "";

            reader.beginObject();
            while (reader.hasNext()) {
                String tokenName = reader.nextName();
                switch (tokenName) {
                    case "userID":
                        userID = reader.nextInt();
                        break;
                    case "username":
                        username = reader.nextString();
                        break;
                    case "email":
                        email = reader.nextString();
                        break;
                    case "password":
                        password = reader.nextString();
                        break;
                }
            }
            reader.endObject();

            User user = new User(userID, username, email, password);
            users.add(user);
        }
        reader.endArray();
    }

    public List<User> getUsers() {
        return users;
    }

    public Map<String, List<String>> getGroups() {
        return groups;
    }

    public Map<String, List<String>> getMemberships() {
        return memberships;
    }
}
