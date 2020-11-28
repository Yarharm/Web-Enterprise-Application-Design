import business_layer.UserManager;
import org.junit.Before;
import org.junit.Test;

public class UserManagerTest {
    private static final String CIRCULAR_DEPENDENCY_DIFFERENT_GROUP_TEST_DATA = "circularDependencyDifferentGroup.json";
    private static final String CIRCULAR_DEPENDENCY_SAME_GROUP_DATA = "circularDependencySameGroup.json";
    private static final String MISSING_PARENT_GROUP_DATA = "missingParentData.json";
    private static final String SUCCESSFUL_USER_DATA = "successfulUser.json";
    private static final String USER_NAME = null;


    UserManager userManager = null;

    @Before
    public void before() {
        userManager = new UserManager();
    }

    @Test(expected = Exception.class)
    public void testCircularDependencyDifferentGroup() {
        userManager.loadGroupMembership(CIRCULAR_DEPENDENCY_DIFFERENT_GROUP_TEST_DATA,USER_NAME);
    }
    @Test
    public void testSuccessfulUser() {
        userManager.loadGroupMembership(SUCCESSFUL_USER_DATA,USER_NAME);
    }
    @Test(expected = Exception.class)
    public void testCircularDependencySameGroup() {
        userManager.loadGroupMembership(CIRCULAR_DEPENDENCY_SAME_GROUP_DATA,USER_NAME);
    }

    @Test(expected = Exception.class)
    public void testMissingParentGroup() {
        userManager.loadGroupMembership(MISSING_PARENT_GROUP_DATA,USER_NAME);
    }
}
