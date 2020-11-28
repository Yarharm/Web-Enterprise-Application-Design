package business_layer;
import com.google.gson.stream.JsonReader;
import helpers.Utils;
import models.User;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class UserManager {
    private final static String USER_FILE_NAME = "users.json";
    private final static String USER_GROUP_ADMIN = "admins";
    private final List<User> users;
    private final Map<String, List<String>> groups;
    private final Map<String, List<String>> memberships;
    private final Set<String> groupNames;
    private boolean finishedLoadingData;

    public UserManager() {
        users = new ArrayList<>();
        groups = new HashMap<>();
        memberships = new HashMap<>();
        groupNames = new HashSet<>(Collections.singletonList(""));
        finishedLoadingData = false;
    }

    public Set<String> loadGroupMembership(String username) throws Exception {
        return this.loadGroupMembership(username, USER_FILE_NAME);
    }

    public Set<String> loadGroupMembership(String username, String userConfig) throws Exception {
        fetchUserInformation(userConfig);
        if(!users.stream().map(User::getUsername).collect(Collectors.toList()).containsAll(memberships.keySet())) {
            throw new Exception("Group membership with undefined user");
        }
        if(!groupNames.equals(groups.keySet())) {
            throw new Exception("Group membership with non-existing parent");
        }
        if(!memberships.containsKey(username)) {
            throw new Exception("Missing " + username + " membership information");
        }
        if(!groups.keySet().containsAll(memberships.get(username))) {
            throw new Exception("Membership information contains undefined group");
        }

        Set<String> currentUserMemberships = new HashSet<>();
        for(String membershipGroup : memberships.get(username)) {
            collectChildGroups(membershipGroup, currentUserMemberships, new HashSet<>());
        }
        return currentUserMemberships;
    }

    private void collectChildGroups(String currentGroup, Set<String> children, Set<String> visitedGroups) throws Exception {
        if(visitedGroups.contains(currentGroup)) {
            throw new Exception("Detected circular dependency");
        }
        visitedGroups.add(currentGroup);
        children.add(currentGroup);
        for(String childGroup : groups.get(currentGroup)) {
            collectChildGroups(childGroup, children, visitedGroups);
        }
        visitedGroups.remove(currentGroup);
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
                e.printStackTrace();
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
            groupNames.add(name);
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
