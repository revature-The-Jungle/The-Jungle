package dev.com.thejungle.service.implementations;

import dev.com.thejungle.customexception.DuplicateUsername;
import dev.com.thejungle.customexception.UnallowedSpaces;
import dev.com.thejungle.dao.UserDAO;
import dev.com.thejungle.entity.User;
import dev.com.thejungle.service.interfaces.UserService;

public class UserServiceImp implements UserService {

    UserDAO userDAO;

    public UserServiceImp (UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public User createNewUserService(User user) {
        try {
            return null;
        } catch (DuplicateUsername d) {
            throw new DuplicateUsername("This username is already taken.");
        } catch (UnallowedSpaces s) {
            throw new UnallowedSpaces("No spaces allowed in username or password.");
        }
    }





    @Override
    public User searchForUserService(String username) {
        return null;
    }

    @Override
    public User loginService() {
        return null;
    }
}
