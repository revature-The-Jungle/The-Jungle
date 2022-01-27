package dev.com.thejungle.dao.implementations;

import dev.com.thejungle.customexception.DuplicateEmail;
import dev.com.thejungle.customexception.DuplicateUsername;
import dev.com.thejungle.customexception.UserNotFound;
import dev.com.thejungle.dao.interfaces.UserDAOInt;
import dev.com.thejungle.entity.User;
import dev.com.thejungle.utility.ConnectionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAO implements UserDAOInt {


    /**
     * connects to database to create a new User
     * @param user Object that contains information of the user
     * @return User that was created in the database
     */
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
            preparedStatement.setDate(7, new Date(user.getUserBirthdate()));
            preparedStatement.setString(8, user.getImageFormat());
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            user.setUserId(rs.getInt("user_id"));
            return user;
        } catch (SQLException q) {
            if (q.getMessage().contains("username")){
                throw new DuplicateUsername("This username is already taken");
            }
            else if (q.getMessage().contains("email")){
                throw new DuplicateEmail("Email is already in use");
            }
            else {
                q.printStackTrace();
                return null;
            }
        }
    }

    /**
     * connects to the database to search for a User using username and retrieve its results
     * @param username username to search by
     * @return ArrayList of Users matching the search result
     */
    @Override
    public User searchForUser(String username) {
        try(Connection connection = ConnectionDB.createConnection()) {
            String sql = "select * from user_table where username = %?%";
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
                        resultSet.getDate("user_birth_date").getTime(),
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

    /**
     * connects to the database to retrieve all existing Users
     * @return List of Users
     */
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
                        resultSet.getDate("user_birth_date").getTime(),
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

    /**
     * connects to the database to retrieve list of groups that a specific user is in
     * @param userId id of user to search by
     * @return ArrayList of Integer filled with groupIds
     */
    @Override
    public ArrayList<Integer> getGroups(int userId) {
        try (Connection connection = ConnectionDB.createConnection()) {
            String sql = "select group_id from group_member_junction_table where user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Integer> groupIds = new ArrayList<>();
            while(resultSet.next()){
               groupIds.add(resultSet.getInt("group_id"));
            }
            return groupIds;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

}
