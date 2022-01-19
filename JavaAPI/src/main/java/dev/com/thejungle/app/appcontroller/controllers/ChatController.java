package dev.com.thejungle.app.appcontroller.controllers;

import dev.com.thejungle.dao.implementations.ChatDAO;
import dev.com.thejungle.service.implementations.ChatService;
import io.javalin.http.Handler;

public class ChatController {
    ChatService chatService;

    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    public Handler connectTochat =  ws -> {
        ws.onConnect(ctx -> {

            String username = "User"  ;
            userUsernameMap.put(ctx, ctx.pathParam("id"));
            broadcastMessage(userUsernameMap.get(ctx), ctx.getSessionId() ,ctx.pathParam("id"));
        });
        ws.onClose(ctx -> {
            String username = userUsernameMap.get(ctx);
            userUsernameMap.remove(ctx);
            broadcastMessage(userUsernameMap.get(ctx), ctx.getSessionId() ,ctx.pathParam("id"));
        });
        ws.onMessage(ctx -> {
            broadcastMessage(userUsernameMap.get(ctx), ctx.message() ,ctx.pathParam("id"));
        });
    });
}
