package dev.com.thejungle.service.implementations;

import dev.com.thejungle.dao.implementations.ChatDAO;
import dev.com.thejungle.entity.ChatMessage;
import dev.com.thejungle.service.interfaces.ChatServiceInt;

import java.util.ArrayList;

public class ChatService implements ChatServiceInt {

    private ChatDAO chatDAO;

    public ChatService(ChatDAO chatDAO) {
        this.chatDAO = chatDAO;
    }

    @Override
    public ChatMessage serviceCreateMessage(int chatId, int userId, String chatDate, String chatContent) {
        return chatDAO.createMessage(chatId, userId, chatDate, chatContent);
    }

    @Override
    public ArrayList<ChatMessage> serviceGetMessageHistory() {
        return chatDAO.getMessageHistory();
    }
}
