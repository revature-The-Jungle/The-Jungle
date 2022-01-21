package dao;

import dev.com.thejungle.dao.interfaces.ChatDAOInt;
import dev.com.thejungle.dao.implementations.ChatDAO;
import dev.com.thejungle.entity.ChatMessage;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class ChatDAOTests {

    ChatDAO chatDAO = new ChatDAO();

    /**
     * createMessage with user_id
     * Success
     */
    @Test
    public void createMessageSuccessChatId(){
        ChatMessage testCreateMessage = chatDAO.createMessage(10000, "", 10000, 10000, "Hi");
//        Assert.assertNotNull(testCreateMessage.getChatId());
        Assert.assertTrue(testCreateMessage.getChatId()!= 0);
    }
    /**
     * createMessage with user_id
     * Fail
     */
    @Test
    public void createMessageFailChatId(){
        ChatMessage testCreateMessage = chatDAO.createMessage(99999, "", 99999, 99999, "Nine Thousand");
        Assert.assertNull(testCreateMessage); //The user/data was entering incorrect information into the system, hence giving an error.

    }

    /**
     * getMessageHistory with no groupId
     * Success
     */
    @Test
    public void getMessageHistorySuccessGlobalChat() {
        ArrayList<ChatMessage> chatMessages = chatDAO.getMessageHistory();
        Assert.assertNotNull(chatMessages);
    }

    /**
     * getMessageHistory with existing groupId
     * Success
     */
    @Test
    public void getMessageHistorySuccessGroupChat() {
        ArrayList<ChatMessage> chatMessages = chatDAO.getMessageHistory(10000);
        Assert.assertNotNull(chatMessages);
    }

    /**
     * getMessageHistory with non-existing group
     * Fail
     */
    @Test
    public void getMessageHistoryFailNoGroup() {
        ArrayList<ChatMessage> chatMessages = chatDAO.getMessageHistory(999999);
        Assert.assertNotNull(chatMessages);
    }

}
