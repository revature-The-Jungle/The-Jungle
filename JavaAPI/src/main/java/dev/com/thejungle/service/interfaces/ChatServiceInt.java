package dev.com.thejungle.service.interfaces;

import dev.com.thejungle.entity.ChatMessage;

import java.util.ArrayList;

public interface ChatServiceInt {

    ChatMessage serviceCreateMessage(int chatId, int userId, String chatDate, int group_id, String chatContent);

    ArrayList<ChatMessage> serviceGetMessageHistory(String currentTime);
}
