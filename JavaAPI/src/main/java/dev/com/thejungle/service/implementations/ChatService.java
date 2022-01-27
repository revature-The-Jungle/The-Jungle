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

    /**
     * calls createMessage in ChatDao
     * @param chatMessage
     * @return creating Message for the user using three parameters and looks through if they're less than zero or not.
     * If it is less than it will throw an exception
     */
    @Override
    public ChatMessage serviceCreateMessageObject(ChatMessage chatMessage){
        if(chatMessage.getUserId() <= 0 || chatMessage.getGroupId() <= 0 || chatMessage.getChatContent().isEmpty()){
            throw new InvalidInputException();
        }
        return chatDAO.createMessage(chatMessage);
    }

    /**
     * calls getMessageHistory in ChatDAO for Group Chatroom
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
     * calls getMessageHistory in ChatDAO for Global Chatroom
     * @return ArrayList of ChatMessage objects from 5 minutes ago in global chat room
     * Will return empty ArrayList if no messages
     */
    @Override
    public ArrayList<ChatMessage> serviceGetMessageHistory() {
        return chatDAO.getMessageHistory();
    }
}
