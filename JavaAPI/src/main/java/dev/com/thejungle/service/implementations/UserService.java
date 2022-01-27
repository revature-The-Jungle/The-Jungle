package dev.com.thejungle.service.implementations;

import dev.com.thejungle.customexception.*;
import dev.com.thejungle.dao.implementations.UserDAO;
import dev.com.thejungle.dao.interfaces.UserDAOInt;
import dev.com.thejungle.entity.User;
import dev.com.thejungle.service.interfaces.UserServiceInt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class UserService implements UserServiceInt {

    private final UserDAO userDAO;

    public UserService (UserDAO userDAO) {
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
        } catch (BlankInputs b) {
            throw new BlankInputs("Please fill in the blanks");
        } catch (DuplicateEmail e) {
            throw new DuplicateEmail("Email is already in use");
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
    public User loginService(String username, String passcode) {
        User newUser = this.userDAO.searchForUser(username);
        if ((username.length() > 20) || (passcode.length() > 30))
            throw new TooManyCharacters("You are exceeding your character limit");
        if ((username.length() == 0) || (passcode.length() == 0))
            throw new NoValuePasscode("You must enter a passcode");
        if (!Objects.equals(newUser.getUsername(), username) || !Objects.equals(newUser.getPasscode(), passcode))
            throw new UsernameOrPasscodeException("Username or Passcode are incorrect");
        return newUser;
    }


    @Override
    public List<User> getAllUsersService() {
        return this.userDAO.getAllUsers();
    }

    @Override
    public HashMap<Integer, String> getGroupsNames(int userId) {
        if (userId > 0) {
            return this.userDAOInt.getGroupsNames(userId);
        } else {
            throw new InvalidInputException("User Id needs to be positive");
        }
    }
    @Override
    public ArrayList<Integer> getGroups(int userId) {
        try {
            if (userId > 0) {
                if (userId < 1000000) {
                    return this.userDAO.getGroups(userId);
                } else {
                    throw new InvalidInputException("User Id needs to be positive and in range");
                }
            } else {
                throw new InvalidInputException("User Id needs to be positive and in range");
            }
        } catch (UserNotFound e) {
            throw new UserNotFound("User not found");
        }
    }
}

