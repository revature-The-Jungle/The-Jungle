package service;

import dev.com.thejungle.customexception.InvalidInputException;
import dev.com.thejungle.dao.implementations.ChatDAO;
import dev.com.thejungle.entity.ChatMessage;
import dev.com.thejungle.service.implementations.ChatService;
import org.testng.annotations.Test;

public class ChatServiceTests {

    ChatService chatService = new ChatService(new ChatDAO());

    /**
     * serviceCreateMessage with invalid userId
     */
    @Test(expectedExceptions = InvalidInputException.class, expectedExceptionsMessageRegExp = "Invalid User ID")
    public void serviceCreateMessageFailInvalidChatId(){
        ChatMessage chatMessage = new ChatMessage(-1, 9000, "hi");
        chatService.serviceCreateMessageObject(chatMessage);
    }

    /**
     * serviceCreateMessage with invalid groupId
     */
    @Test(expectedExceptions = InvalidInputException.class, expectedExceptionsMessageRegExp = "Invalid Group ID")
    public void serviceCreateMessageFailInvalidGroupId(){
        ChatMessage chatMessage = new ChatMessage(9000, -1, "hi");
        chatService.serviceCreateMessageObject(chatMessage);
    }
    /**
     * serviceCreateMessage with Long Content
     */
    @Test(expectedExceptions = InvalidInputException.class, expectedExceptionsMessageRegExp = "Long Content")
    public void serviceCreateMessageWithLongContent(){
        ChatMessage chatMessage = new ChatMessage(9000, 9000, "N".repeat(301));
        chatService.serviceCreateMessageObject(chatMessage);
    }

    /**
     * serviceCreateMessage with invalid chatContent
     */
    @Test(expectedExceptions = InvalidInputException.class, expectedExceptionsMessageRegExp = "Invalid Chat Content")
    public void serviceCreateMessageFailInvalidChatContent(){
        ChatMessage chatMessage = new ChatMessage(9000, 9000, "");
        chatService.serviceCreateMessageObject(chatMessage);
    }

    /**
     * serviceGetMessageHistory with 0 as groupId
     */
    @Test(expectedExceptions = InvalidInputException.class, expectedExceptionsMessageRegExp = "Invalid Input Exception")
    public void serviceGetMessageHistoryFailInvalidGroupId() {
        chatService.serviceGetMessageHistory(0);
    }

    /**
     * serviceGetMessageHistory with negative groupId
     */
    @Test(expectedExceptions = InvalidInputException.class, expectedExceptionsMessageRegExp = "Invalid Input Exception")
    public void serviceGetMessageHistoryFailInvalidGroupId2() {
        chatService.serviceGetMessageHistory(-5);
    }
}
