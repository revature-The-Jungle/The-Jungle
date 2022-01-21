package dev.com.thejungle.app.appcontroller.controllers;

import com.google.gson.Gson;
import dev.com.thejungle.dao.implementations.ChatDAO;
import dev.com.thejungle.entity.ChatMessage;
import dev.com.thejungle.service.implementations.ChatService;
import io.javalin.websocket.WsConfig;
import io.javalin.websocket.WsContext;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatController {


    public static Map<WsContext, String> userUsernameMap = new ConcurrentHashMap<>();


    ChatService chatService;

    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    public static void connectToWebSocket(WsConfig ws){
        ws.onConnect(ctx -> {
            String username = "User"  ;
            userUsernameMap.put(ctx, ctx.pathParam("id"));
            broadcastMessage(userUsernameMap.get(ctx), "has connected to chat" );

        });
        ws.onClose(ctx -> {
            String username = userUsernameMap.get(ctx);
            userUsernameMap.remove(ctx);
            broadcastMessage(userUsernameMap.get(ctx), ctx.getSessionId() );
        });
        ws.onMessage(ctx -> {
            Gson gson = new Gson();
            ChatMessage messageJson = gson.fromJson(ctx.message(), ChatMessage.class);
            chatService.serviceCreateMessage(messageJson);
            broadcastMessage(userUsernameMap.get(ctx), ctx.message());
        });
    };

    public static void broadcastMessage(String senderId, String message) {

        userUsernameMap.keySet().stream().filter(ctx -> (ctx.session.isOpen() && userUsernameMap.get(ctx) == senderId)).forEach(session -> {
            System.out.println(session);
            Gson gson = new Gson();
            Map<String, Object> broadcastString = new HashMap<>();
            broadcastString.put("userName", sender);
            broadcastString.put("userMessage", message);
            broadcastString.put("userList", userUsernameMap.values());
            session.send(gson.toJson(broadcastString));
        });
    }
}
