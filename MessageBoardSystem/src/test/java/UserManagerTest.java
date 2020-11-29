import helpers.Utils;
import separated_management.UserManager;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import java.util.Set;
import java.util.HashSet;

import static org.junit.Assert.assertTrue;
public class UserManagerTest {
    private static final String CIRCULAR_DEPENDENCY_DIFFERENT_GROUP_TEST_DATA = "circularDependencyDifferentGroup.json";
    private static final String CIRCULAR_DEPENDENCY_SAME_GROUP_DATA = "circularDependencySameGroup.json";
    private static final String MISSING_PARENT_GROUP_DATA = "missingParentData.json";
    private static final String SUCCESSFUL_USER_DATA = "successfulUser.json";
    private static final String UNDEFINED_USER_DATA = "undefinedUserData.json";
    private final static String UNDEFINED_GROUP_DATA = "undefinedGroupData.json";
    private static final String USER_NAME_BOB = "Bob";
    private static final String USER_NAME_MAX = "Max";
    private static final String USER_NAME_SARA = "Sara";

    @Test(expected = Exception.class)
    public void testCircularDependencyDifferentGroup() throws Exception {
        UserManager userManager = new UserManager(Utils.buildTargetFilePath(CIRCULAR_DEPENDENCY_DIFFERENT_GROUP_TEST_DATA));
        userManager.loadGroupMembership(USER_NAME_BOB);
    }

    @Test(expected = Exception.class)
    public void testCircularDependencySameGroup() throws Exception {
        UserManager userManager = new UserManager(Utils.buildTargetFilePath(CIRCULAR_DEPENDENCY_SAME_GROUP_DATA));
        userManager.loadGroupMembership(USER_NAME_MAX);
    }

    @Test(expected = Exception.class)
    public void testMissingParentGroup() throws Exception {
        UserManager userManager = new UserManager(Utils.buildTargetFilePath(MISSING_PARENT_GROUP_DATA));
        userManager.loadGroupMembership(USER_NAME_BOB);
    }

    @Test(expected = Exception.class)
    public void testUndefinedUser() throws Exception {
        UserManager userManager = new UserManager(Utils.buildTargetFilePath(UNDEFINED_USER_DATA));
        userManager.loadGroupMembership(USER_NAME_BOB);
    }

    @Test(expected = Exception.class)
    public void testUndefinedGroup() throws Exception {
        UserManager userManager = new UserManager(Utils.buildTargetFilePath(UNDEFINED_GROUP_DATA));
        userManager.loadGroupMembership(USER_NAME_BOB);
    }

    @Test
    public void testSuccessfulUserBob() throws Exception {
        UserManager userManager = new UserManager(Utils.buildTargetFilePath(SUCCESSFUL_USER_DATA));
        Set<String> col1 = userManager.loadGroupMembership(USER_NAME_BOB);
        Set<String>col2 = new HashSet<>();
        col2.add("admins");
        col2.add("concordia");
        col2.add("encs");
        col2.add("comp");
        col2.add("soen");
        assertTrue(CollectionUtils.isEqualCollection(col2, col1));
    }

    @Test
    public void testSuccessfulUserMax() throws Exception {
        UserManager userManager = new UserManager(Utils.buildTargetFilePath(SUCCESSFUL_USER_DATA));
        Set<String> col1 = userManager.loadGroupMembership(USER_NAME_MAX);
        Set<String>col2 = new HashSet<>();
        col2.add("encs");
        col2.add("comp");
        col2.add("soen");
        assertTrue(CollectionUtils.isEqualCollection(col2, col1));
    }

    @Test
    public void testSuccessfulUserSara() throws Exception {
        UserManager userManager = new UserManager(Utils.buildTargetFilePath(SUCCESSFUL_USER_DATA));
        Set<String> col1 = userManager.loadGroupMembership(USER_NAME_SARA);
        Set<String>col2 = new HashSet<>();
        col2.add("soen");
        assertTrue(CollectionUtils.isEqualCollection(col2, col1));
    }
}
