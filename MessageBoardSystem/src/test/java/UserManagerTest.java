import business_layer.UserManager;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertTrue;
public class UserManagerTest {
    private static final String CIRCULAR_DEPENDENCY_DIFFERENT_GROUP_TEST_DATA = "circularDependencyDifferentGroup.json";
    private static final String CIRCULAR_DEPENDENCY_SAME_GROUP_DATA = "circularDependencySameGroup.json";
    private static final String MISSING_PARENT_GROUP_DATA = "missingParentData.json";
    private static final String SUCCESSFUL_USER_DATA = "successfulUser.json";
    private static final String USER_NAME=null;
    private static final String USER_NAME_BOB = "Bob";
    private static final String USER_NAME_MAX = "Max";
    private static final String USER_NAME_SARA = "Sara";


    UserManager userManager = null;

    @Before
    public void before() {
        userManager = new UserManager();
    }

    @Test(expected = Exception.class)
    public void testCircularDependencyDifferentGroup() {
        userManager.loadGroupMembership(CIRCULAR_DEPENDENCY_DIFFERENT_GROUP_TEST_DATA,USER_NAME);
    }
    @Test(expected = Exception.class)
    public void testCircularDependencySameGroup() {
        userManager.loadGroupMembership(CIRCULAR_DEPENDENCY_SAME_GROUP_DATA,USER_NAME);
    }

    @Test(expected = Exception.class)
    public void testMissingParentGroup() {
        userManager.loadGroupMembership(MISSING_PARENT_GROUP_DATA,USER_NAME);
    }


    @Test
    public void testSuccessfulUserBob() {
        Set<String> col1 = userManager.loadGroupMembership(SUCCESSFUL_USER_DATA,USER_NAME_BOB);
        Set<String>col2 = null;
        col2.add("admins");
        col2.add("concordia");
        col2.add("encs");
        col2.add("comp");
        col2.add("soen");
        assertTrue(CollectionUtils.isEqualCollection(col2, col1));
    }
    @Test
    public void testSuccessfulUserMax() {
        Set<String> col1 = userManager.loadGroupMembership(SUCCESSFUL_USER_DATA,USER_NAME_MAX);
        Set<String>col2 = null;
        col2.add("encs");
        col2.add("comp");
        col2.add("soen");
        assertTrue(CollectionUtils.isEqualCollection(col2, col1));
    }
    @Test
    public void testSuccessfulUserSara() {
        Set<String> col1 = userManager.loadGroupMembership(SUCCESSFUL_USER_DATA,USER_NAME_SARA);
        Set<String>col2 = null;
        col2.add("soen");
        assertTrue(CollectionUtils.isEqualCollection(col2, col1));
    }
}