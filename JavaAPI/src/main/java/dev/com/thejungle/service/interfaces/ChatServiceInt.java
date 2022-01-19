package dev.com.thejungle.service.interfaces;

import dev.com.thejungle.entity.ChatMessage;

import java.util.ArrayList;

public interface ChatServiceInt {

    ChatMessage serviceCreateMessage(int chatId, String chatDate, int userId, int group_id, String chatContent);

    ArrayList<ChatMessage> serviceGetMessageHistory(int groupId);

    ArrayList<ChatMessage> serviceGetMessageHistory();
}
