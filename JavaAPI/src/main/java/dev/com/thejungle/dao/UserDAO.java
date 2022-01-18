package dev.com.thejungle.dao;

public interface UserDAO {

    UserDAO createNewUser();

    UserDAO searchForUser();

    UserDAO login();
}
