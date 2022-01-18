package dev.com.thejungle.dao.implementations;

import dev.com.thejungle.dao.interfaces.ChatDAOInt;
import dev.com.thejungle.entity.ChatMessage;
import dev.com.thejungle.utility.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;

public class ChatDAO implements ChatDAOInt {

    @Override
    public ChatMessage createMessage(int chatId, int userId, String chatDate, String chatContent) {
//        try (Connection connection = ConnectionDB.createConnection()) {
//            String sql = "insert into chat_log values()";
//        } catch (SQLException e) {
//            return null;
//        }
    return null;
    }
}
