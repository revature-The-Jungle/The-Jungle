package dev.com.thejungle.entity;

import java.util.Objects;

public class ChatMessage {

    private int chatId;
    private int userId;
    private String chatDate;
    private String chatContent;

    public ChatMessage() {

    }

    public ChatMessage(int userId, int chatId, String chatDate, String chatContent) {
        this.setUserId(userId);
        this.setChatId(chatId);
        this.setChatDate(chatDate);
        this.setChatContent(chatContent);
    }
    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getChatDate() {
        return chatDate;
    }

    public void setChatDate(String chatDate) {
        this.chatDate = chatDate;
    }

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "userId=" + getUserId() +
                ", chatId=" + getChatId() +
                ", chatDate='" + getChatDate() + '\'' +
                ", chatContent='" + getChatContent() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessage that = (ChatMessage) o;
        return getChatId() == that.getChatId() && getUserId() == that.getUserId() && Objects.equals(getChatDate(), that.getChatDate()) && Objects.equals(getChatContent(), that.getChatContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getChatId(), getUserId(), getChatDate(), getChatContent());
    }

}
