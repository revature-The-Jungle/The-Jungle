package dev.com.thejungle.dao.interfaces;

import dev.com.thejungle.entity.User;
import java.util.List;


public interface UserDAOInt {

    User createNewUser(User user);

    User searchForUser(String username);

    List<User> getAllUsers();

}
