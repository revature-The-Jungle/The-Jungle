package service;

import dev.com.thejungle.customexception.InvalidInputException;
import dev.com.thejungle.dao.implementations.ChatDAO;
import dev.com.thejungle.entity.ChatMessage;
import dev.com.thejungle.service.implementations.ChatService;
import org.testng.annotations.Test;

public class ChatServiceTests {

    ChatService chatService = new ChatService(new ChatDAO());

    /**
     * serviceCreateMessage with 0 as chatId
     */

    @Test(expectedExceptions = InvalidInputException.class, expectedExceptionsMessageRegExp = "Invalid Input Exception")
    public void serviceCreateMessageFailInvalidChatId(){
        ChatMessage chatMessage = new ChatMessage(0, 0, "");
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
