package dao;

import dev.com.thejungle.customexception.BlankInputs;
import dev.com.thejungle.dao.implementations.UserDAO;
import dev.com.thejungle.entity.User;
import org.asynchttpclient.util.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.sql.Date;
import java.util.List;

import static org.testng.AssertJUnit.fail;

public class UserDAOIntTests {

    UserDAO userDAOInt = new UserDAO();

    // TEST FOR USER CREATION/REGISTRATION
    @Test
    void testCreateNewUser() {
        Date date = new Date(742892400000L);
        User newJungleUser = new User(0, "Test", "Tester", "testingemail77@gmail.com",
                "userna", "passcode", "I like social media.", date,
                "imagesourcefile");
        User createdUser = userDAOInt.createNewUser(newJungleUser);
        Assert.assertEquals(createdUser.getFirstName(), "Test");
    }


    // SAD PATH, FOUND BLANK INPUTS
    @Test
    void testCreateNewUserBlankInputsSad(){
        try {
            Date date = new Date(742892400000L);
            User newJungleUser = new User(0, "Test", "Tester", "testingel77@gmail.com",
                    "usernam", "", "", date,
                    "imagesourcefile");
            User badUserInfo = userDAOInt.createNewUser(newJungleUser);
            fail();
        } catch (BlankInputs b) {
            Assert.assertEquals("Please fill in the blanks", b.getMessage());
        }
    }



    // TEST TO SEARCH BY USERNAME
    @Test
    void testGetUserByUsername() {
        User newJungleUser = userDAOInt.searchForUser("username");
        System.out.println("new user is " + newJungleUser);
        Assert.assertEquals(newJungleUser.getUsername(), "username");
    }


    @Test
    void testGetAllUsers() {
        List<User> users = userDAOInt.getAllUsers();
        for (User u : users)
            System.out.println(u);
        Assert.assertTrue(users.size() >= 1);
    }

}
