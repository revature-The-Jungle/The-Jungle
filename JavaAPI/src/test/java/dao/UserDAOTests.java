package dao;

import dev.com.thejungle.customexception.DuplicateEmail;
import dev.com.thejungle.customexception.DuplicateUsername;
import dev.com.thejungle.customexception.UserNotFound;
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
        User newJungleUser = new User(0, "Test", "Tester", "emailavd;orin2",
                        "useravoin", "passcode", "I like social media.", date,
                        "imagesourcefile");
        User createdUser = userDAO.createNewUser(newJungleUser);
        Assert.assertEquals(createdUser.getFirstName(), "Test");
    }

    // SAD PATH TEST DUPLICATE USERNAME
    @Test
    void testDuplicateUsernameException(){
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
    void testDuplicateEmailException(){
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


    // TEST TO SEARCH BY USERNAME
    @Test
    void testGetUserByUsername() {
        User newJungleUser = userDAO.searchForUser("username");
        System.out.println("new user is " + newJungleUser);
        Assert.assertEquals(newJungleUser.getUsername(), "username");
    }

    @Test
    void testGetUserByUsernameSecond() {
        User newJungleUser = userDAO.searchForUser("username3");
        System.out.println("new user is " + newJungleUser);
        Assert.assertEquals(newJungleUser.getUsername(), "username3");
    }

    //  GET ALL USERS
    @Test
    void testGetAllUsers() {
        List<User> users = userDAO.getAllUsers();
        for (User u : users)
            System.out.println(u);
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
    void testGetGroups(){
        ArrayList<Integer> arrayList = userDAO.getGroups(9000);
        Assert.assertTrue(arrayList.size() >= 1);
    }

    @Test
    void testGetGroupsTwo(){
        ArrayList<Integer> arrayList = userDAO.getGroups(13);
        Assert.assertTrue(arrayList.size() >= 1);
    }



    //  ------------------------------  DAO Sad path -----------------------------------

    @Test
    void testGetGroupsBad(){
        ArrayList<Integer> arrayList = userDAO.getGroups(9000000);
        Assert.assertNotNull(arrayList);
    }

    @Test(expectedExceptions = UserNotFound.class, expectedExceptionsMessageRegExp = "User not found")
    void testGetUserByUsernameBad() {
        userDAO.searchForUser("notAUsername");
    }

}
