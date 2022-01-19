package dev.com.thejungle.dao.interfaces;

import dev.com.thejungle.entity.ChatMessage;

import java.util.ArrayList;

public interface ChatDAOInt {

    ChatMessage createMessage(int chatId, String chatDate, int userId, int groupId, String chatContent);

    ArrayList<ChatMessage> getMessageHistory(int groupId);

    ArrayList<ChatMessage> getMessageHistory();
}
