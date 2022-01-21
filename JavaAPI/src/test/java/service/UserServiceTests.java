package service;

import dev.com.thejungle.customexception.DuplicateUsername;
import dev.com.thejungle.customexception.UnallowedSpaces;
import dev.com.thejungle.customexception.UsernameOrPasscodeException;
import dev.com.thejungle.dao.implementations.UserDAO;
import dev.com.thejungle.entity.User;
import dev.com.thejungle.service.implementations.UserService;
import dev.com.thejungle.service.interfaces.UserServiceInt;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.sql.Date;
import java.util.List;

public class UserServiceTests {

    public static UserDAO userDAOImp = new UserDAO();
    public static UserServiceInt userServiceImp = new UserService(userDAOImp);

    static User userProfile;
    static User userProfile2;
    static User returnedProfile;
    static User badUsername;
    static User duplicateUsername;
    static User usernameSpaces;
    static User passwordSpaces;
    static List<User> newList;


    @BeforeClass
    public void setup(){
        userDAOImp = Mockito.mock(UserDAO.class);
        userServiceImp = new UserService(userDAOImp);
        Date date = new Date(742892400000L);
        userProfile = new User(0, "Razor", "Ramon", "iwrestleforaliving@gmail.com", "ILoveToWrestle", "MySimplePasscode", "I enjoy the wrestling life", date,"image");
        userProfile2 = new User(1, "Razor", "Ramon", "iwrestleforaliving@gmail.com", "ILoveToWrestle", "MySimplePasscode", "I enjoy the wrestling life", date,"image");
        returnedProfile = new User(2, "Solomon", "Grundy", "solomon@gmail.com", "BornOnMonday", "Tuesday", "I have a poem", date,"image");
        badUsername = new User(0, "Solomon", "Grundy", "solomon@gmail.com", "IAmSolomonGrundy", "Tuesday", "I have a poem", date,"image");
        duplicateUsername = new User(0, "Dup", "Testing", "dup@email.com", "username", "password", "I like social media.", date, "imagesrc");
        usernameSpaces = new User(0, "User", "Testing", "space@email.com", "user name", "password", "I like social media.", date, "imagesrc");
        passwordSpaces = new User(0, "User", "Testing", "space2@email.com", "username", "password space", "I like social media.", date, "imagesrc");
    }



//  ------------------------------------ MOCK TESTS ----------------------------------------


    // DUPLICATE USERNAME
    @Test(expectedExceptions = DuplicateUsername.class, expectedExceptionsMessageRegExp = "This username is already taken")
    public void cannotHaveDuplicateUsername(){
        Mockito.when(userDAOImp.createNewUser(duplicateUsername)).thenThrow(new DuplicateUsername("This username is already taken"));
        userServiceImp.createNewUserService(duplicateUsername);
    }

    // SPACES IN USERNAME
    @Test(expectedExceptions = UnallowedSpaces.class, expectedExceptionsMessageRegExp = "No spaces allowed in username or password")
    public void cannotHaveSpacesInUsername(){
        Mockito.when(userDAOImp.createNewUser(usernameSpaces)).thenThrow(new UnallowedSpaces("No spaces allowed in username or password"));
        userServiceImp.createNewUserService(usernameSpaces);
    }

    // SPACES IN PASSWORD
    @Test(expectedExceptions = UnallowedSpaces.class, expectedExceptionsMessageRegExp = "No spaces allowed in username or password")
    public void cannotHaveSpacesInPassword(){
        Mockito.when(userDAOImp.createNewUser(passwordSpaces)).thenThrow(new UnallowedSpaces("No spaces allowed in username or password"));
        userServiceImp.createNewUserService(passwordSpaces);
    }

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


    @Test
    public void getAllUsersMockito() {
        Mockito.when(userDAOImp.getAllUsers()).thenReturn(newList);
        List<User> result = userServiceImp.getAllUsersService();
        Assert.assertEquals(result, newList);
    }




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