package dev.com.thejungle.dao;

import dev.com.thejungle.entity.User;
import dev.com.thejungle.utility.ConnectionDB;

import java.sql.*;

public class UserDAOImp implements UserDAO {

    @Override
    public User createNewUser(User user) {
        try (Connection connection = ConnectionDB.createConnection()) {
            String sql = "insert into user_table values(default, '', '', '', '', '', '', '', ''";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setString(5, user.getPasscode());
            preparedStatement.setString(6, user.getUserAbout());
//            preparedStatement.setString(7, user.getUserBirthdate());
//            preparedStatement.setString(8, user.getImageFormat());
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            user.setUserId(rs.getInt("user_id"));
            return user;
        } catch (SQLException q) {
            q.printStackTrace();
            return null;
        }
    }


    @Override
    public User searchForUser(String username) {
        return null;
    }


    @Override
    public User login() {
        return null;
    }
}
