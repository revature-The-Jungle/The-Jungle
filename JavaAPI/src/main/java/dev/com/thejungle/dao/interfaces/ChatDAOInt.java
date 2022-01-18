package dev.com.thejungle.dao.interfaces;

import dev.com.thejungle.entity.ChatMessage;

public interface ChatDAOInt {

    ChatMessage createMessage(int chatId, int userId, String chatDate, String chatContent);
}
