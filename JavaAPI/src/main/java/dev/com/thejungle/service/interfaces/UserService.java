package dev.com.thejungle.service.interfaces;

import dev.com.thejungle.entity.User;

public interface UserService {

    User createNewUserService(User user);

    User searchForUserService(String username);

    User loginService(String username, String passcode);

}
