package tests;

import business_layer.UserManager;
import models.User;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

public class UserTests {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void definedUser() throws Exception {
        UserManager um = new UserManager();
        ArrayList<String> groups = um.getGroups(new User(1, "Bob", "testemail", "1234"));
        Assert.assertEquals(true, groups.contains("placeholder1"));
        Assert.assertEquals(true, groups.contains("placeholder2"));
    }

    @Test(expected = java.lang.Exception.class)
    public void undefinedUser() throws Exception {
        UserManager um = new UserManager();
        um.getGroups(new User(999, "TestUser", "fakeemail", "9999"));
        expectedException.expect(java.lang.Exception.class);
        expectedException.expectMessage("This user does not exist.");
    }
}
