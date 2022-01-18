package dao;

import dev.com.thejungle.dao.UserDAO;
import org.testng.Assert;
import org.testng.annotations.Test;

public class userDAOTests {

    UserDAO userDAO = new UserDAOImp();

    @Test
    void testGetUserByUsername() {
        JungleUser newJungleUser = jungleUserDAO.getUserByUsername("");
        System.out.println("new user is " + newJungleUser);
        Assert.assertEquals(newJungleUser.getUsername(), "");
}
