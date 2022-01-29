package service;

import dev.com.thejungle.customexception.*;
import dev.com.thejungle.dao.implementations.UserDAO;
import dev.com.thejungle.entity.User;
import dev.com.thejungle.service.implementations.UserService;
import dev.com.thejungle.service.interfaces.UserServiceInt;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTests {

    public static UserDAO userDAO = new UserDAO();
    public static UserServiceInt userService = new UserService(userDAO);

    static User userProfile;
    static User userProfile2;
    static User returnedProfile;
    static User badUsername;
    static User duplicateUsername;
    static User usernameSpaces;
    static User passwordSpaces;
    static List<User> newList;
    static ArrayList<User> anotherList = new ArrayList<>();
    static User badPasscode;
    static User otherProfile;
    static User blankSpaces;
    static User duplicateEmailUser;


    @BeforeClass
    public void setup() {
        userDAO = Mockito.mock(UserDAO.class);
        userService = new UserService(userDAO);
        long date = 742892400000L;
        userProfile = new User(0, "Razor", "Ramon", "iwrestleforaliving@gmail.com", "ILoveToWrestle", "MySimplePasscode", "I enjoy the wrestling life", date, "image");
        badPasscode = new User(0, "Razor", "Ramon", "iwrestleforaliving@gmail.com", "ILove", "Wrong", "I enjoy the wrestling life", date, "image");
        userProfile2 = new User(1, "Razor", "Ramon", "iwrestleforaliving@gmail.com", "ILoveToWrestle", "MySimplePasscode", "I enjoy the wrestling life", date, "image");
        returnedProfile = new User(2, "Solomon", "Grundy", "solomon@gmail.com", "BornOnMonday", "Tuesday", "I have a poem", date, "image");
        otherProfile = new User(3, "Solomon", "Grundy", "solomon@gmail.com", "BornOnMonday", "Tuesday", "I have a poem", date, "image");
        badUsername = new User(0, "Solomon", "Grundy", "solomon@gmail.com", "IAmSolomonGrundy", "Tuesday", "I have a poem", date, "image");
        duplicateUsername = new User(0, "Dup", "Testing", "dup@email.com", "username", "password", "I like social media.", date, "imagesrc");
        usernameSpaces = new User(0, "User", "Testing", "space@email.com", "user name", "password", "I like social media.", date, "imagesrc");
        passwordSpaces = new User(0, "User", "Testing", "space2@email.com", "username", "password space", "I like social media.", date, "imagesrc");
        anotherList.add(userProfile);
        anotherList.add(userProfile2);
        blankSpaces = new User(0, "Test", "", "email@testemail.com", "", "", "Social media is fun.", date, "imagesrc");
        duplicateEmailUser = new User(0, "Dup", "Testing", "testingemail@gmail.com", "avoihoih", "password", "I like social media.", date, "imagesrc");
    }


//  ------------------------------------ MOCK TESTS ----------------------------------------


    // DUPLICATE USERNAME
    @Test(expectedExceptions = DuplicateUsername.class, expectedExceptionsMessageRegExp = "This username is already taken")
    public void cannotHaveDuplicateUsernameSuccess() {
        Mockito.when(userDAO.createNewUser(duplicateUsername)).thenThrow(new DuplicateUsername("This username is already taken"));
        userService.createNewUserService(duplicateUsername);
    }

    // DUPLICATE EMAIL
    @Test(expectedExceptions = DuplicateEmail.class, expectedExceptionsMessageRegExp = "Email is already in use")
    public void cannotHaveDuplicateEmailSuccess() {
        Mockito.when(userDAO.createNewUser(duplicateEmailUser)).thenThrow(new DuplicateEmail("Email is already in use"));
        userService.createNewUserService(duplicateEmailUser);
    }

    // SPACES IN USERNAME
    @Test(expectedExceptions = UnallowedSpaces.class, expectedExceptionsMessageRegExp = "No spaces allowed in username or password")
    public void cannotHaveSpacesInUsernameSuccess() {
        Mockito.when(userDAO.createNewUser(usernameSpaces)).thenThrow(new UnallowedSpaces("No spaces allowed in username or password"));
        userService.createNewUserService(usernameSpaces);
    }

    // SPACES IN PASSWORD
    @Test(expectedExceptions = UnallowedSpaces.class, expectedExceptionsMessageRegExp = "No spaces allowed in username or password")
    public void cannotHaveSpacesInPasswordSuccess() {
        Mockito.when(userDAO.createNewUser(passwordSpaces)).thenThrow(new UnallowedSpaces("No spaces allowed in username or password"));
        userService.createNewUserService(passwordSpaces);
    }

    // BLANK INPUTS
    @Test(expectedExceptions = BlankInputs.class, expectedExceptionsMessageRegExp = "Please fill in the blanks")
    public void missingInputsForUserRegistrationSuccess() {
        Mockito.when(userDAO.createNewUser(blankSpaces)).thenThrow(new BlankInputs("Please fill in the blanks"));
        userService.createNewUserService(blankSpaces);
    }

    // Get GroupId
    @Test(expectedExceptions = InvalidInputException.class)
    public void getGroupMockito() {
        Mockito.when(userDAO.getGroups(-2)).thenThrow(InvalidInputException.class);
        userService.getGroups(-2);
    }

    @Test(expectedExceptions = InvalidInputException.class)
    public void getGroupNoIdMockito() {
        Mockito.when(userDAO.getGroups(0)).thenThrow(InvalidInputException.class);
        userService.getGroups(0);
    }

    // Get All
    @Test
    public void getAllUsersMockito() {
        Mockito.when(userDAO.getAllUsers()).thenReturn(newList);
        List<User> result = userService.getAllUsersService();
        Assert.assertEquals(result, newList);
    }

    @Test
    public void getAllUsersTwoMockito() {
        Mockito.when(userDAO.getAllUsers()).thenReturn(anotherList);
        List<User> result = userService.getAllUsersService();
        Assert.assertEquals(result, anotherList);
    }

    // REQUEST LOGIN
    @Test(expectedExceptions = NoValuePasscode.class, expectedExceptionsMessageRegExp = "You must enter username and password")
    public void loginServiceFailEmptyCredentials() {
        userService.loginService("", "");
    }

    @Test(expectedExceptions = TooManyCharacters.class, expectedExceptionsMessageRegExp = "You are exceeding your character limit")
    public void loginServiceFailLongCredentials() {
        userService.loginService("123456789012345678901234567890123455786829034586902348690324806823904608932406892309486098209234068809", "hello");
    }

    // GET USER BY ID
    @Test(expectedExceptions = InvalidInputException.class, expectedExceptionsMessageRegExp = "Invalid Input: UserId Must Be A Non 0 Positive")
    public void getUserByIdServiceFailInvalidUserId() {
        userService.getUserByIdService(0);
    }

    // SEARCH FOR USER BY USERNAME
    @Test(expectedExceptions = InvalidInputException.class, expectedExceptionsMessageRegExp = "Invalid Input: Empty Username")
    public void searchForUserServiceFailEmptyUsername() {
        userService.searchForUserService("");

    }

    @Test(expectedExceptions = InvalidInputException.class, expectedExceptionsMessageRegExp = "Invalid Input: UserName Exceeds 50 Characters")
    public void searchForUserServiceFailLongUsername() {
        userService.searchForUserService("123456789012345678901234567890123455786829034586902348690324806823904608932406892309486098209234068809");
    }
}
