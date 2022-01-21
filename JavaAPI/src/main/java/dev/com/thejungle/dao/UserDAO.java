package dev.com.thejungle.dao;

import dev.com.thejungle.entity.User;

public interface UserDAO {

    User createNewUser();

    User searchForUser(String username);

    User login();
}
