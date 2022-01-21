package service;

import dev.com.thejungle.customexception.DuplicateUsername;
import dev.com.thejungle.dao.implementations.UserDAOImp;
import dev.com.thejungle.entity.User;
import dev.com.thejungle.service.implementations.UserServiceImp;
import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Date;

public class userServiceTests {

    public static UserDAOImp userDAOImp;
    public static UserServiceImp userServiceImp;


    @BeforeClass
    public void setup(){
        userDAOImp = Mockito.mock(UserDAOImp.class);
        userServiceImp = new UserServiceImp(userDAOImp);
    }


    @Test(expectedExceptions = DuplicateUsername.class, expectedExceptionsMessageRegExp = "This username is already taken.")
    void cannotHaveDuplicateUsername(){
        Date date = new Date(742892400000L);
        User newJungleUser = new User(0, "Test", "Tester", "testingemail2@gmail.com",
                "username5", "passcode", "I like social media.", date,
                "imagesourcefile");
        Mockito.when(userDAOImp.createNewUser(newJungleUser.getUsername()).thenThrow(new DuplicateUsername
                ("This username is already taken.")));
    }



}
