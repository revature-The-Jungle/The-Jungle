package service;

import dev.com.thejungle.customexception.DuplicateUsername;
import dev.com.thejungle.dao.UserDAOImp;
import dev.com.thejungle.entity.User;
import dev.com.thejungle.service.implementations.UserServiceImp;
import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
        User newJungleUser = new User(0, 'Test', 'Tester', 'testingemail@gmail.com',
                'username', 'passcode', 'I like social media. I sign up for everything.',
                '1980-01-01', 'imagesourcefile');
        Mockito.when(userDAOImp.createNewUser(newJungleUser.getUsername()).thenThrow(new DuplicateUsername
                ("This username is already taken.")));
    }
    


}
