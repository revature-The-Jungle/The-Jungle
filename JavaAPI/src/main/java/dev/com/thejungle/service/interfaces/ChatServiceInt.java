package dev.com.thejungle.service.interfaces;

import dev.com.thejungle.entity.ChatMessage;

public interface ChatServiceInt {

    public ChatMessage serviceCreateMessage(int chatId, int userId, String chatDate, String chatContent);
}
