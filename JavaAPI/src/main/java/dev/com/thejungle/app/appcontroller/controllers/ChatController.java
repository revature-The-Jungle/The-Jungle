package dev.com.thejungle.app.appcontroller.controllers;

import com.google.gson.Gson;
import dev.com.thejungle.dao.implementations.ChatDAO;
import dev.com.thejungle.entity.ChatMessage;
import dev.com.thejungle.service.implementations.ChatService;
import io.javalin.websocket.WsConfig;
import io.javalin.websocket.WsContext;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatController {


    public Map<WsContext, Integer> userUsernameMap = new ConcurrentHashMap<>();


    ChatService chatService;

    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    public void connectToWebSocket(WsConfig ws){
        ws.onConnect(ctx -> {
            ArrayList<ChatMessage> messages;
            userUsernameMap.put(ctx, Integer.parseInt(ctx.pathParam("id")));
            if(Integer.parseInt(ctx.pathParam("id")) == 0){
                messages= chatService.serviceGetMessageHistory();}
            else{
                messages = chatService.serviceGetMessageHistory(Integer.parseInt(ctx.pathParam("id")));}

            for(ChatMessage message : messages){
                broadcastMessage();
            }

        });
        ws.onClose(ctx -> {
            userUsernameMap.remove(ctx);

        });
        ws.onMessage(ctx -> {
            System.out.println(ctx.message());
            Gson gson = new Gson();
            Map<Object,String> messageJson = gson.fromJson(ctx.message(), Map.class);
            System.out.println(messageJson);
            int userId = Integer.parseInt( messageJson.get("userId"));
            int groupId = userUsernameMap.get(ctx);
            String chatContent = messageJson.get("chatContent");
            String userName =messageJson.get("userName");
            ChatMessage chatMessage = new ChatMessage(userId,groupId,chatContent);
            ChatMessage returnedChat = chatService.serviceCreateMessageObject(chatMessage);
            broadcastMessage(returnedChat.getChatId(),returnedChat.getUserId(),returnedChat.getChatContent(),userName,returnedChat.getChatDate(),groupId);
        });
    };

    public void broadcastMessage(int chatId, int userId, String chatContent,String userName,String date, int groupId) {

        userUsernameMap.keySet().stream().filter(ctx -> (ctx.session.isOpen() && userUsernameMap.get(ctx) == groupId)).forEach(session -> {
            System.out.println(session);
            Gson gson = new Gson();
            Map<String, Object> broadcastString = new HashMap<>();
            broadcastString.put("chatId",chatId);
            broadcastString.put("userId",userId);
            broadcastString.put("chatContent", chatContent);
            broadcastString.put("userName", userName);
            broadcastString.put("date",date);
            session.send(gson.toJson(broadcastString));
        });
    }
}
