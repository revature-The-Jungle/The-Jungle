package dev.com.thejungle.dao.implementations;

import dev.com.thejungle.dao.interfaces.ChatDAOInt;
import dev.com.thejungle.entity.ChatMessage;
import dev.com.thejungle.utility.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;

public class ChatDAO implements ChatDAOInt {

    @Override
    public ChatMessage createMessage(int chatId, String chatDate, int userId, int group_id, String chatContent) {
        try (Connection connection = ConnectionDB.createConnection()) {
            String sql = "insert into chat_log_table values(?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, chatId);
            preparedStatement.setString(2, chatDate);
            preparedStatement.setInt(3, userId);
            preparedStatement.setInt(4, group_id);
            preparedStatement.setString(5, chatContent);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new ChatMessage(
                    resultSet.getInt("chat_id"),
                    resultSet.getString("chat_date"),
                    resultSet.getInt("userId"),
                    resultSet.getInt("group_id"),
                    resultSet.getString("chatContent")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * connects to database to retrieve messages from 5 minutes ago in group chat
     * @param groupId
     * @return ArrayList of ChatMessage objects from 5 minutes ago in group chat room
     * Will return empty ArrayList if no messages
     */
    @Override
    public ArrayList<ChatMessage> getMessageHistory(int groupId) {
        try (Connection connection = ConnectionDB.createConnection()) {
                String sql = "select * from chat_log_table where chat_date >= now() - interval '5 minutes' and group_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, groupId);
                ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<ChatMessage> chatMessages = new ArrayList<>();
            while (resultSet.next()) {
                chatMessages.add(new ChatMessage(
                        resultSet.getInt("chat_id"),
                        resultSet.getString("chat_date"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("group_id"),
                        resultSet.getString("chat_content")
                ));
            }
            return chatMessages;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * connects to database to retrieve messages from 5 minutes ago in global chat
     * @return ArrayList of ChatMessage objects from 5 minutes ago in group chat room
     * Will return empty ArrayList if no messages
     */
    @Override
    public ArrayList<ChatMessage> getMessageHistory() {
        try (Connection connection = ConnectionDB.createConnection()) {
            String sql = "select * from chat_log_table where chat_date >= now() - interval '5 minutes' and group_id = null";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            ArrayList<ChatMessage> chatMessages = new ArrayList<>();
            while (resultSet.next()) {
                chatMessages.add(new ChatMessage(
                        resultSet.getInt("chat_id"),
                        resultSet.getString("chat_date"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("group_id"),
                        resultSet.getString("chat_content")
                ));
            }
            return chatMessages;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}