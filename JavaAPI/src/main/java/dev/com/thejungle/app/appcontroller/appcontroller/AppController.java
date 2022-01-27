package dev.com.thejungle.app.appcontroller.appcontroller;

import dev.com.thejungle.app.appcontroller.controllers.ChatController;
import dev.com.thejungle.app.appcontroller.controllers.UserController;
import io.javalin.Javalin;

public class AppController {
    private ChatController chatController;
    private UserController userController;
    private Javalin app;

    public AppController(Javalin app, ChatController chatController, UserController userController) {
        this.chatController = chatController;
        this.userController = userController;
        this.app = app;
    }

    public void createChatRoutes() {
        app.ws("/chat/{id}/{userName}", chatController::connectToWebSocket);
    }

    public void createUserRoutes() {
        app.get("/user/{username}", userController.getUserByUsername);
        app.get("/users", userController.getAllUsers);
        app.post("/user/login", userController.loginUser);
        app.get("/user/group/{userId}", userController.getGroups);
        app.post("/user/registration", userController.registerUser);
    }
}


