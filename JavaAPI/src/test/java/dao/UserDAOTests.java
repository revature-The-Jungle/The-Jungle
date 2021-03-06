package dao;

import dev.com.thejungle.customexception.*;
import dev.com.thejungle.dao.implementations.UserDAO;
import dev.com.thejungle.entity.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class UserDAOTests {

    UserDAO userDAO = new UserDAO();

    // TEST FOR USER CREATION/REGISTRATION
    @Test
    void testCreateNewUser() {
        long date = 742892400000L;
        User newJungleUser = new User(0, "Test", "Tester", "avawoianv@.c0",
                "useravoinavoin", "passcode", "I like social media.", date,
                "imagesourcefile");
        User createdUser = userDAO.createNewUser(newJungleUser);
        Assert.assertEquals(createdUser.getFirstName(), "Test");
    }

    // SAD PATH TEST DUPLICATE USERNAME
    @Test
    void testDuplicateUsernameException() {
        try {
            long date = 742892400000L;
            User newJungleUser = new User(0, "Test", "Tester", "email123@emai",
                    "username", "passcode", "I like social media.", date,
                    "imagesourcefile");
            userDAO.createNewUser(newJungleUser);
        } catch (DuplicateUsername d) {
            Assert.assertEquals("This username is already taken", d.getMessage());
        }
    }


    // SAD PATH TEST DUPLICATE EMAIL
    @Test
    void testDuplicateEmailException() {
        try {
            long date = 742892400000L;
            User newJungleUser = new User(0, "Test", "Tester", "email",
                    "usernamehsrtn", "passcode", "I like social media.", date,
                    "imagesourcefile");
            userDAO.createNewUser(newJungleUser);
        } catch (DuplicateEmail e) {
            Assert.assertEquals("Email is already in use", e.getMessage());
        }
    }

    // TEST GET USER BY ID

    // TEST REQUEST LOGIN
    @Test
    public void testRequestLoginSuccess() {
        User user = userDAO.requestLogin("username", "passcode");
        Assert.assertTrue(user.getUsername().equals("username"));
    }

    @Test
    public void testRequestLoginSuccess2() {
        User user = userDAO.requestLogin("test", "createpost");
        Assert.assertTrue(user.getUsername().equals("test"));
    }

    // TEST TO SEARCH BY USERNAME
    @Test
    void testSearchUserSuccess() {
        ArrayList<User> users = userDAO.searchForUser("username");
        for (User user : users) {
            if (!user.getUsername().contains("username")) {
                Assert.fail();
            }
        }
        Assert.assertTrue(true);
    }

    @Test
    void testSearchUserSuccess2() {
        ArrayList<User> users = userDAO.searchForUser("username3");
        for (User user : users) {
            if (!user.getUsername().contains("username3")) {
                Assert.fail();
            }
        }
        Assert.assertTrue(true);

    }

    @Test
    void testGetUserByIdSuccess() {
        User user = userDAO.getUserById(9000);
        Assert.assertTrue(user.getUserId() == 9000);
    }

    @Test
    void testGetUserByIdSuccess2() {
        User user = userDAO.getUserById(10000);
        Assert.assertTrue(user.getUserId() == 10000);
    }

    //  GET ALL USERS
    @Test
    void testGetAllUsers() {
        List<User> users = userDAO.getAllUsers();
        for (User u : users)
            Assert.assertTrue(users.size() >= 1);
    }

    @Test
    void testGetAllUsersTwo() {
        List<User> users = userDAO.getAllUsers();
        for (User u : users)
            System.out.println(u);
        Assert.assertTrue(users.size() <= 200);
    }


    // GET GROUPS
    @Test
    void testGetGroups() {
        ArrayList<Integer> arrayList = userDAO.getGroups(9000);
        Assert.assertTrue(arrayList.size() >= 1);
    }

    @Test
    void testGetGroupsTwo() {
        ArrayList<Integer> arrayList = userDAO.getGroups(13);
        Assert.assertTrue(arrayList.size() >= 1);
    }


    //  ------------------------------  DAO Sad path -----------------------------------

    @Test(expectedExceptions = InvalidInputException.class, expectedExceptionsMessageRegExp = "Invalid Input Exception")
    void testGetGroupsBad() {
        userDAO.getGroups(9000000);
    }

    @Test
    void testSearchUserByUsernameBad() {
        ArrayList<User> users = userDAO.searchForUser("notAUsername");
        Assert.assertTrue(users.isEmpty());
    }

    @Test(expectedExceptions = UserNotFound.class, expectedExceptionsMessageRegExp = "User Not Found")
    void testGetUserByIdFailNoUserFound() {
        userDAO.getUserById(9996784);
    }

    @Test(expectedExceptions = UsernameOrPasscodeException.class, expectedExceptionsMessageRegExp = "User Not Found")
    void testRequestLoginFailWrongPassword() {
        userDAO.requestLogin("username", "33");
    }

    @Test(expectedExceptions = UsernameOrPasscodeException.class, expectedExceptionsMessageRegExp = "User Not Found")
    void testRequestLoginFailWrongUsername() {
        userDAO.requestLogin("ttt", "passcode");
    }

    @Test(expectedExceptions = UsernameOrPasscodeException.class, expectedExceptionsMessageRegExp = "User Not Found")
    void testRequestLoginFailWrongUsernameAndPassword() {
        userDAO.requestLogin("wert", "asdghj");
    }
}
