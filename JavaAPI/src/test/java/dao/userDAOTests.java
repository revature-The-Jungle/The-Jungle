package dao;

import dev.com.thejungle.dao.UserDAO;
import dev.com.thejungle.dao.implementations.UserDAOImp;
import dev.com.thejungle.entity.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Date;

public class userDAOTests {

    UserDAO userDAO = new UserDAOImp();


//    create table user_table(
//            user_id serial primary key,
//            first_name varchar(20) not null,
//    last_name varchar(20) not null,
//    email varchar(50) unique not null,
//    username varchar(50) unique not null,
//    passcode varchar(50) not null,
//    user_about varchar(500),
//    user_birth_date DATE not null,
//    image_format varchar(50)
//);


    // TEST FOR USER CREATION/REGISTRATION
    @Test
    void testCreateNewUser() {
        User newJungleUser = new User(0, "Test", "Tester", "testingemail@gmail.com",
                "username3", "passcode", "I like social media.", "1980-01-01",
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
