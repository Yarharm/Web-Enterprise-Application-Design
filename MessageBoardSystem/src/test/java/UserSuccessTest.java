import business_layer.UserManager;
import org.junit.Before;
import org.junit.Test;

public class UserSuccessTest {
    private static final String SUCCESSFUL_USER_DATA = "successfulUser.json";

    UserManager userManager = null;
    @Before
    public void before() {
        userManager = new UserManager();
    }

    @Test
    public void testSuccessfulUser() {
        userManager.loadGroupMembership(SUCCESSFUL_USER_DATA);
    }
}
