package dev.com.thejungle.service.interfaces;

import dev.com.thejungle.entity.User;

import java.util.List;

public interface UserServiceInt {

    User createNewUserService(User user);

    User searchForUserService(String username);

    User loginService(String username, String passcode);

    List<User> getAllUsersService();

}
