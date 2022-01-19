package dev.com.thejungle.service.implementations;

import dev.com.thejungle.customexception.InvalidInputException;
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
    public ChatMessage serviceCreateMessage(int chatId, String chatDate, int userId, int group_id, String chatContent) {
        return chatDAO.createMessage(chatId, chatDate, userId, group_id, chatContent);
    }


    /**
     * calls getMessageHistory in ChatDAO
     * @param groupId
     * @return ArrayList of ChatMessage objects from 5 minutes ago in group chat room
     * Will return empty ArrayList if no messages
     */
    @Override
    public ArrayList<ChatMessage> serviceGetMessageHistory(int groupId) {
        if (groupId < 1) {
            throw new InvalidInputException();
        }
        return chatDAO.getMessageHistory(groupId);
    }

    /**
     * calls getMessageHistory in ChatDAO
     * @return ArrayList of ChatMessage objects from 5 minutes ago in global chat room
     * Will return empty ArrayList if no messages
     */
    @Override
    public ArrayList<ChatMessage> serviceGetMessageHistory() {
        return chatDAO.getMessageHistory();
    }
}
