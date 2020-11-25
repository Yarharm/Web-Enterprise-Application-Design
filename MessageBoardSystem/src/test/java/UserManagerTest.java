import business_layer.UserManager;
import org.junit.Before;
import org.junit.Test;

public class UserManagerTest {
    private static final String CIRCULAR_DEPENDENCY_DIFFERENT_GROUP_TEST_DATA = "circularDependencyDifferentGroup.json";
    private static final String CIRCULAR_DEPENDENCY_SAME_GROUP_DATA = "circularDependencySameGroup.json";
    private static final String MISSING_PARENT_GROUP_DATA = "missingParentData.json";

    UserManager userManager = null;

    @Before
    public void before() {
        userManager = new UserManager();
    }

    @Test(expected = Exception.class)
    public void testCircularDependencyDifferentGroup() {
        userManager.loadGroupMembership(CIRCULAR_DEPENDENCY_DIFFERENT_GROUP_TEST_DATA);
    }

    @Test(expected = Exception.class)
    public void testCircularDependencySameGroup() {
        userManager.loadGroupMembership(CIRCULAR_DEPENDENCY_SAME_GROUP_DATA);
    }

    @Test(expected = Exception.class)
    public void testMissingParentGroup() {
        userManager.loadGroupMembership(MISSING_PARENT_GROUP_DATA);
    }
}
