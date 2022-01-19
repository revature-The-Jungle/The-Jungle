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



    @Test
    void testGetUserByUsername() {
        User newJungleUser = userDAO.searchForUser("");
        System.out.println("new user is " + newJungleUser);
        Assert.assertEquals(newJungleUser.getUsername(), "");
    }



    // TESTS FOR USER CREATION/REGISTRATION
    @Test
    void testCreateNewUser() {
        User newJungleUser = new User();
    }

}



