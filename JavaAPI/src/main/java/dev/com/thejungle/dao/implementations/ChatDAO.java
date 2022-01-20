package dev.com.thejungle.dao.implementations;

import dev.com.thejungle.dao.interfaces.ChatDAOInt;
import dev.com.thejungle.entity.ChatMessage;
import dev.com.thejungle.utility.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChatDAO implements ChatDAOInt {

    @Override
    public ChatMessage createMessage(int chatId, int userId, String chatDate, int group_id, String chatContent) {
        try (Connection connection = ConnectionDB.createConnection()) {
            String sql = "insert into chat_log_table values(?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, chatId);
            preparedStatement.setString(2,chatDate);
            preparedStatement.setInt(3, userId);
            preparedStatement.setInt(4,group_id);
            preparedStatement.setString(5, chatContent);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new ChatMessage(
                    resultSet.getInt("chatId"),
                    resultSet.getInt("chatDate"),
                    resultSet.getInt("userId"),
                    resultSet.getString("group_id"),
                    resultSet.getString("chatContent")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<ChatMessage> getMessageHistory(String currentTime) {
    return null;
    }
}