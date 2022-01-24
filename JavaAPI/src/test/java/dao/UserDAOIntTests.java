package dao;

import dev.com.thejungle.customexception.DuplicateEmail;
import dev.com.thejungle.customexception.DuplicateUsername;
import dev.com.thejungle.dao.implementations.UserDAO;
import dev.com.thejungle.entity.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.sql.Date;
import java.util.List;

public class UserDAOIntTests {

    UserDAO userDAOInt = new UserDAO();

    // TEST FOR USER CREATION/REGISTRATION
    @Test
    void testCreateNewUser() {
        Date date = new Date(742892400000L);
        User newJungleUser = new User(0, "Test", "Tester", "email123@emaileowun",
                        "usernametestasdovun", "passcode", "I like social media.", date,
                        "imagesourcefile");
        User createdUser = userDAOInt.createNewUser(newJungleUser);
        Assert.assertEquals(createdUser.getFirstName(), "Test");
    }

    // SAD PATH TEST DUPLICATE USERNAME
    @Test
    void testDuplicateUsernameException(){
        try {
            Date date = new Date(742892400000L);
            User newJungleUser = new User(0, "Test", "Tester", "email123@emai",
                        "username", "passcode", "I like social media.", date,
                        "imagesourcefile");
            userDAOInt.createNewUser(newJungleUser);
        } catch (DuplicateUsername d) {
            Assert.assertEquals("This username is already taken", d.getMessage());
        }
    }


    // SAD PATH TEST DUPLICATE EMAIL
    @Test
    void testDuplicateEmailException(){
        try {
            Date date = new Date(742892400000L);
            User newJungleUser = new User(0, "Test", "Tester", "email",
                    "usernamehsrtn", "passcode", "I like social media.", date,
                    "imagesourcefile");
            userDAOInt.createNewUser(newJungleUser);
        } catch (DuplicateEmail e) {
            Assert.assertEquals("Email is already in use", e.getMessage());
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
