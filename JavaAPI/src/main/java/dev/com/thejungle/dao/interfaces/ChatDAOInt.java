package dev.com.thejungle.dao.interfaces;

import dev.com.thejungle.entity.ChatMessage;

import java.util.ArrayList;

public interface ChatDAOInt {

    ChatMessage createMessage(int chatId, int userId, String chatDate, String chatContent);

    ArrayList<ChatMessage> getMessageHistory(String currentTime);
}
