package service;

import dev.com.thejungle.customexception.UsernameOrPasscodeException;
import dev.com.thejungle.dao.implementations.UserDAOImp;
import dev.com.thejungle.entity.User;
import dev.com.thejungle.service.implementations.UserServiceImp;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserServiceTests {

    public static UserDAOImp userDAOImp = new UserDAOImp();
    public static UserServiceImp userServiceImp = new UserServiceImp(userDAOImp);

    static User userProfile;
    static User userProfile2;
    static User returnedProfile;
    static User badUsername;


    @BeforeClass
    public void setup(){
        userDAOImp = Mockito.mock(UserDAOImp.class);
        userServiceImp = new UserServiceImp(userDAOImp);
        userProfile = new User(0, "Razor", "Ramon", "iwrestleforaliving@gmail.com", "ILoveToWrestle", "MySimplePasscode", "I enjoy the wrestling life", "1980-01-01","image");
        userProfile2 = new User(1, "Razor", "Ramon", "iwrestleforaliving@gmail.com", "ILoveToWrestle", "MySimplePasscode", "I enjoy the wrestling life", "1980-01-01","image");
        returnedProfile = new User(2, "Solomon", "Grundy", "solomon@gmail.com", "BornOnMonday", "Tuesday", "I have a poem", "1980-02-01","image");
        badUsername = new User(0, "Solomon", "Grundy", "solomon@gmail.com", "IAmSolomonGrundy", "Tuesday", "I have a poem", "1980-02-01","image");

    }


//    @Test(expectedExceptions = DuplicateUsername.class, expectedExceptionsMessageRegExp = "This username is already taken.")
//    void cannotHaveDuplicateUsername(){
//        User newJungleUser = new User(0, 'Test', 'Tester', 'testingemail@gmail.com',
//                'username', 'passcode', 'I like social media. I sign up for everything.',
//                '1980-01-01', 'imagesourcefile');
//        Mockito.when(userDAOImp.createNewUser(newJungleUser.getUsername()).thenThrow(new DuplicateUsername
//                ("This username is already taken.")));
//    }
//  ------------------------------------ MOCK TESTS ----------------------------------------

    //  NOT FOUND USER
    @Test(expectedExceptions = UsernameOrPasscodeException.class)
    public void loginCredentialsIncorrect() {
        Mockito.when(userDAOImp.searchForUser("Wayne")).thenThrow(new UsernameOrPasscodeException("Username or Passcode are incorrect"));
        userServiceImp.loginService("Wayne", "Rain");
    }

    // BAD USERNAME
    @Test(expectedExceptions = UsernameOrPasscodeException.class)
    public void BadUsernameForMockito() {
        Mockito.when(userDAOImp.searchForUser(userProfile.getUsername())).thenReturn(badUsername);
        userServiceImp.loginService(userProfile.getUsername(), badUsername.getPasscode());
    }

    // BAD PASSCODE
//    @Test(expectedExceptions =  UsernameOrPasscodeException.class)
//    public void BadPasscodeForMockito() {
//        Mockito.when(userDAOImp)
//    }





//  ----------------------------------  STUBBED TESTS --------------------------------------
    @Test
    public void userLoginSuccess() {
        Mockito.when(userDAOImp.searchForUser("BornOnMonday")).thenReturn(returnedProfile);
        User result = userServiceImp.loginService("BornOnMonday", "Tuesday");
        Assert.assertEquals(result, returnedProfile);
    }


    @Test
    public void searchForUserByUsernameMockito(){
        Mockito.when(userDAOImp.searchForUser(userProfile.getUsername())).thenReturn(userProfile2);
        User result = userServiceImp.searchForUserService(userProfile.getUsername());
        Assert.assertEquals(result, userProfile2);
    }

}
