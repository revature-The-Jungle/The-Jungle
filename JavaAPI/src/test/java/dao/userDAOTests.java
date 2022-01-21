package dao;

import dev.com.thejungle.dao.UserDAO;
import dev.com.thejungle.dao.implementations.UserDAOImp;
import dev.com.thejungle.entity.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Date;

public class userDAOTests {

    UserDAO userDAO = new UserDAOImp();


    // TEST FOR USER CREATION/REGISTRATION
    @Test
    void testCreateNewUser() {
        Date date = new Date(742892400000L);
        User newJungleUser = new User(0, "Test", "Tester", "testingemail2@gmail.com",
                "username5", "passcode", "I like social media.", date,
                "imagesourcefile");
        User createdUser = userDAO.createNewUser(newJungleUser);
        Assert.assertEquals(createdUser.getFirstName(), "Test");
    }



//    @Test
//    void testGetUserByUsername() {
//        User newJungleUser = userDAO.searchForUser("");
//        System.out.println("new user is " + newJungleUser);
//        Assert.assertEquals(newJungleUser.getUsername(), "");
//    }

}
