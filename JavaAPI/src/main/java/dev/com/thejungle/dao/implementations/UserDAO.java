package dev.com.thejungle.dao.implementations;

import dev.com.thejungle.customexception.BlankInputs;
import dev.com.thejungle.customexception.UserNotFound;
import dev.com.thejungle.dao.interfaces.UserDAOInt;
import dev.com.thejungle.entity.User;
import dev.com.thejungle.utility.ConnectionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAO implements UserDAOInt {

    public static boolean areStringsEmpty(String... strings) {
        for (String s : strings)
            if (s == null || s.isEmpty())
                return true;
        return false;
    }

    @Override
    public User createNewUser(User user) {
        try (Connection connection = ConnectionDB.createConnection()) {
            String sql = "insert into user_table values(default, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setString(5, user.getPasscode());
            preparedStatement.setString(6, user.getUserAbout());
            preparedStatement.setDate(7, user.getUserBirthdate());
            preparedStatement.setString(8, user.getImageFormat());
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            boolean emptyStrings = areStringsEmpty(user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername(),
                    user.getPasscode(), user.getUserAbout(), user.getImageFormat());
            if(emptyStrings == false){
                user.setUserId(rs.getInt("user_id"));
                return user;
            } else {
                throw new BlankInputs("Please fill in the blanks");
            }
        } catch (SQLException q) {
            q.printStackTrace();
            return null;
        }
    }


    @Override
    public User searchForUser(String username) {
        try(Connection connection = ConnectionDB.createConnection()) {
            String sql = "select * from user_table where username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                User newUser = new User (
                        resultSet.getInt("user_Id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("username"),
                        resultSet.getString("passcode"),
                        resultSet.getString("user_about"),
                        resultSet.getDate("user_birth_date"),
                        resultSet.getString("image_format")
                );
                return newUser;
            } else {
                throw new UserNotFound("User not found");
            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<User> getAllUsers() {
        try (Connection connection = ConnectionDB.createConnection()) {
            String sql = "select * from user_table";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("user_Id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("username"),
                        resultSet.getString("passcode"),
                        resultSet.getString("user_about"),
                        resultSet.getDate("user_birth_date"),
                        resultSet.getString("image_format")
                );
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
