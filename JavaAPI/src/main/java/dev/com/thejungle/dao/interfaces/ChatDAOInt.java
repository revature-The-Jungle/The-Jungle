package dev.com.thejungle.dao.interfaces;

import dev.com.thejungle.entity.ChatMessage;

import java.util.ArrayList;

public interface ChatDAOInt {

    ChatMessage createMessage(int chatId, int userId, String chatDate, int group_id, String chatContent);

    ArrayList<ChatMessage> getMessageHistory();
}
