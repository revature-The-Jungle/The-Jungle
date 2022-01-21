package dev.com.thejungle.entity;

import java.util.Objects;

public class ChatMessage {

    private int chatId;
    private String chatDate;
    private int userId;
    private int groupId;
    private String chatContent;

    public ChatMessage() {

    }

    public ChatMessage(int chatId, String chatDate, int userId,int groupId, String chatContent) {
        this.setChatId(chatId);
        this.setChatDate(chatDate);
        this.setUserId(userId);
        this.setGroupId(groupId);
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
  
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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
                ", groupId=" + getGroupId() +
                ", chatDate='" + getChatDate() + '\'' +
                ", chatContent='" + getChatContent() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessage that = (ChatMessage) o;
        return getChatId() == that.getChatId() && getUserId() == that.getUserId() && getGroupId() == that.getGroupId() &&Objects.equals(getChatDate(), that.getChatDate()) && Objects.equals(getChatContent(), that.getChatContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getChatId(), getUserId(), getGroupId(), getChatDate(), getChatContent());
    }

}
