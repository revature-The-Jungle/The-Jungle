package dao;

import dev.com.thejungle.customexception.UserNotFound;
import dev.com.thejungle.dao.implementations.UserDAO;
import dev.com.thejungle.entity.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class UserDAOIntTests {

    UserDAO userDAOInt = new UserDAO();

    // TEST FOR USER CREATION/REGISTRATION
//    @Test
//    void testCreateNewUser() {
//        Date date = new Date(742892400000L);
//        User newJungleUser = new User(0, "Test", "Tester", "testingemail77@gmail.com",
//                "userna", "passcode", "I like social media.", date,
//                "imagesourcefile");
//        User createdUser = userDAOInt.createNewUser(newJungleUser);
//        Assert.assertEquals(createdUser.getFirstName(), "Test");
//    }


    // SEARCH BY USERNAME
    @Test
    void testGetUserByUsername() {
        User newJungleUser = userDAOInt.searchForUser("username");
        System.out.println("new user is " + newJungleUser);
        Assert.assertEquals(newJungleUser.getUsername(), "username");
    }

    @Test
    void testGetUserByUsernameSecond() {
        User newJungleUser = userDAOInt.searchForUser("username3");
        System.out.println("new user is " + newJungleUser);
        Assert.assertEquals(newJungleUser.getUsername(), "username3");
    }

    //  GET ALL USERS
    @Test
    void testGetAllUsers() {
        List<User> users = userDAOInt.getAllUsers();
        for (User u : users)
            System.out.println(u);
        Assert.assertTrue(users.size() >= 1);
    }

    @Test
    void testGetAllUsersTwo() {
        List<User> users = userDAOInt.getAllUsers();
        for (User u : users)
            System.out.println(u);
        Assert.assertTrue(users.size() <= 200);
    }


    // GET GROUPS
    @Test
    void testGetGroups(){
        ArrayList<Integer> arrayList = userDAOInt.getGroups(9000);
        Assert.assertTrue(arrayList.size() >= 1);
    }

    @Test
    void testGetGroupsTwo(){
        ArrayList<Integer> arrayList = userDAOInt.getGroups(13);
        Assert.assertTrue(arrayList.size() >= 1);
    }



    //  ------------------------------  DAO Sad path -----------------------------------

    @Test
    void testGetGroupsBad(){
        ArrayList<Integer> arrayList = userDAOInt.getGroups(9000000);
        Assert.assertNotNull(arrayList);
    }

    @Test(expectedExceptions = UserNotFound.class, expectedExceptionsMessageRegExp = "User not found")
    void testGetUserByUsernameBad() {
        userDAOInt.searchForUser("notAUsername");
    }

}
