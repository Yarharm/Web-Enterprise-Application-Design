package business_layer;
import java.util.*;

public class UserManager {
    private final static String USER_FILE_NAME = "users.json";
    private final static String USER_NAME = null;


    public Set<String> loadGroupMembership() {
        return this.loadGroupMembership(USER_FILE_NAME,USER_NAME);
    }

    public Set<String> loadGroupMembership(String userConfig, String userName) {
        return null;
    }
}
