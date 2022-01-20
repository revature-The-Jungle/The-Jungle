package dao;

import dev.com.thejungle.dao.implementations.ChatDAO;
import dev.com.thejungle.entity.ChatMessage;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class ChatDAOTests {

    ChatDAO chatDAO = new ChatDAO();

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
