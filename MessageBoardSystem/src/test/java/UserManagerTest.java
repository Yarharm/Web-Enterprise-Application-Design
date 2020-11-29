import business_layer.UserManager;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Set;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;
public class UserManagerTest {
    private static final String CIRCULAR_DEPENDENCY_DIFFERENT_GROUP_TEST_DATA = "circularDependencyDifferentGroup.json";
    private static final String CIRCULAR_DEPENDENCY_SAME_GROUP_DATA = "circularDependencySameGroup.json";
    private static final String MISSING_PARENT_GROUP_DATA = "missingParentData.json";
    private static final String SUCCESSFUL_USER_DATA = "successfulUser.json";
    private static final String USER_NAME_BOB = "Bob";
    private static final String USER_NAME_MAX = "Max";
    private static final String USER_NAME_SARA = "Sara";
    private static final String UNDEFINED_USER_DATA = "undefinedUserData.json";
    private final static String UNDEFINED_GROUP_DATA = "undefinedGroupData.json";

    UserManager userManager = null;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void before() {
        userManager = new UserManager();
    }

    @Test(expected = Exception.class)
    public void testCircularDependencyDifferentGroup() {
        userManager.loadGroupMembership(USER_NAME_BOB, CIRCULAR_DEPENDENCY_DIFFERENT_GROUP_TEST_DATA);
    }

    @Test(expected = Exception.class)
    public void testCircularDependencySameGroup() {
        userManager.loadGroupMembership(USER_NAME_MAX, CIRCULAR_DEPENDENCY_SAME_GROUP_DATA);
    }

    @Test(expected = Exception.class)
    public void testMissingParentGroup() {
        userManager.loadGroupMembership(USER_NAME_BOB, MISSING_PARENT_GROUP_DATA);
    }

    @Test
    public void testSuccessfulUserBob() {
        Set<String> col1 = userManager.loadGroupMembership(USER_NAME_BOB, SUCCESSFUL_USER_DATA);
        Set<String>col2 = new HashSet<>();
        col2.add("admins");
        col2.add("concordia");
        col2.add("encs");
        col2.add("comp");
        col2.add("soen");
        assertTrue(CollectionUtils.isEqualCollection(col2, col1));
    }

    @Test
    public void testSuccessfulUserMax() {
        Set<String> col1 = userManager.loadGroupMembership(USER_NAME_MAX, SUCCESSFUL_USER_DATA);
        Set<String>col2 = new HashSet<>();
        col2.add("encs");
        col2.add("comp");
        col2.add("soen");
        assertTrue(CollectionUtils.isEqualCollection(col2, col1));
    }

    @Test
    public void testSuccessfulUserSara() {
        Set<String> col1 = userManager.loadGroupMembership(USER_NAME_SARA, SUCCESSFUL_USER_DATA);
        Set<String>col2 = new HashSet<>();
        col2.add("soen");
        assertTrue(CollectionUtils.isEqualCollection(col2, col1));
    }

    @Test(expected = Exception.class)
    public void undefinedUser(){
        userManager.loadGroupMembership(UNDEFINED_USER_DATA);
    }

    @Test(expected = Exception.class)
    public void undefinedGroup(){
        userManager.loadGroupMembership(UNDEFINED_GROUP_DATA);
    }
}
