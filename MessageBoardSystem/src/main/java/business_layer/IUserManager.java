package business_layer;

import models.User;

import java.util.Set;

public interface IUserManager {
    Set<String> loadGroupMembership(String username) throws Exception;
    User getUser(String email, String password);
}
