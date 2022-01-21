package dao;

import dev.com.thejungle.dao.UserDAO;
import dev.com.thejungle.dao.UserDAOImp;
import dev.com.thejungle.entity.User;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class userDAOTests {

    UserDAO userDAO = new UserDAOImp();



    // TEST FOR USER CREATION/REGISTRATION
    @Test
    void testCreateNewUser() {
        User newJungleUser = new User(0, 'Test', 'Tester', 'testingemail@gmail.com',
                'username', 'passcode', 'I like social media. I sign up for everything.',
                '1980-01-01', 'imagesourcefile');
        User createdUser = userDAO.createNewUser(newJungleUser);
        Assert.assertTrue(createdUser.getUserId() > 0);
    }



    @Test
    void testGetUserByUsername() {
        User newJungleUser = userDAO.searchForUser("");
        System.out.println("new user is " + newJungleUser);
        Assert.assertEquals(newJungleUser.getUsername(), "");
    }

}
