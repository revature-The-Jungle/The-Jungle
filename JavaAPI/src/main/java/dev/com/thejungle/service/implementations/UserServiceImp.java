package dev.com.thejungle.service.implementations;

import dev.com.thejungle.customexception.DuplicateUsername;
import dev.com.thejungle.customexception.UnallowedSpaces;
import dev.com.thejungle.customexception.UserNotFound;
import dev.com.thejungle.customexception.UsernameOrPasscodeException;
import dev.com.thejungle.dao.interfaces.UserDAO;
import dev.com.thejungle.entity.User;
import dev.com.thejungle.service.interfaces.UserService;

import java.util.Objects;

public class UserServiceImp implements UserService {

    UserDAO userDAO;

    public UserServiceImp (UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public User createNewUserService(User user) {
        try {
            if (user.getUsername().matches(".*\\s+.*")) {
                throw new UnallowedSpaces("No spaces allowed in username or password");
            } else if (user.getPasscode().matches(".*\\s+.*")) {
                throw new UnallowedSpaces("No spaces allowed in username or password");
            } else {
                return this.userDAO.createNewUser(user);
            }
        } catch (DuplicateUsername d) {
            throw new DuplicateUsername("This username is already taken");
        }
    }

    @Override
    public User searchForUserService(String username) {
        try {
            return this.userDAO.searchForUser(username);
        } catch (UserNotFound e) {
            throw new UserNotFound("User not found");
        }
    }

    @Override
    public User loginService(String username, String passcode){
        User newUser = this.userDAO.searchForUser(username);
        if (!Objects.equals(newUser.getUsername(), username) || !Objects.equals(newUser.getPasscode(), passcode))
            throw new UsernameOrPasscodeException("Username or Passcode are incorrect");
        return newUser;
    }
}
