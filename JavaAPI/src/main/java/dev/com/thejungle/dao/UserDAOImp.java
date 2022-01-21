package dev.com.thejungle.dao;

import dev.com.thejungle.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImp  implements UserDAO{
    @Override
    public User createNewUser() {
        return null;
    }
    @Override
    public User searchForUser(String username) {
       try (Connection connection = ConnectionFile.createConnection()){
           String sql = "select * from business where username = ?";
           PreparedStatement.preparedStatement = connection.prepareStatement(sql);
           preparedStatement.setString(1, username);
           ResultSet resultSet = preparedStatement.executeQuery();
           User newJungleUser = new User();
           if (resultSet.next()){
               return new User (
                       resultSet.getInt("userId"),
                       resultSet.getString("firstName"),
                       resultSet.getString("lastName"),
                       resultSet.getString("username"),
                       resultSet.getString("password"),
                       resultSet.getString("userAbout"),
                       resultSet.getString("userBirthdate"),
                       resultSet.getString("email"),
                       resultSet.getString("status")
               );
           }
        return newJungleUser;
       }  catch (SQLException e) {
           e.printStackTrace();
           return null;
       }
    }
    @Override
    public User login() {
        return null;
    }
}
