package dev.com.thejungle.app.appcontroller.controllers;

import dev.com.thejungle.service.implementations.ChatService;
import io.javalin.websocket.WsContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatController {


    private static Map<WsContext, String> userUsernameMap = new ConcurrentHashMap<>();


    ChatService chatService;

    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }

//    public WebSocketHandler connectToChat = ws -> {
//        ws.onConnect(ctx -> {
//
//            String username = "User"  ;
//            userUsernameMap.put(ctx, ctx.pathParam("id"));
//            broadcastMessage(userUsernameMap.get(ctx), ctx.getSessionId() ,ctx.pathParam("id"));
//        });
//        ws.onClose(ctx -> {
//            String username = userUsernameMap.get(ctx);
//            userUsernameMap.remove(ctx);
//            broadcastMessage(userUsernameMap.get(ctx), ctx.getSessionId() ,ctx.pathParam("id"));
//        });
//        ws.onMessage(ctx -> {
//            broadcastMessage(userUsernameMap.get(ctx), ctx.message() ,ctx.pathParam("id"));
//        });
//    };
}
