package dev.com.thejungle.app.appcontroller.appcontroller;

import dev.com.thejungle.app.appcontroller.controllers.ChatController;
import dev.com.thejungle.dao.implementations.ChatDAO;
import dev.com.thejungle.service.implementations.ChatService;

public class AppController {
        ChatController chatController;
        public AppController(){
            this.chatController = new ChatController(new ChatService(new ChatDAO()));
        }
        }


