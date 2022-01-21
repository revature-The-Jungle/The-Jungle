package dev.com.thejungle.app.app;

import dev.com.thejungle.app.appcontroller.appcontroller.AppController;
import dev.com.thejungle.dao.implementations.UserDAO;
import dev.com.thejungle.service.implementations.UserService;
import dev.com.thejungle.service.interfaces.UserServiceInt;
import io.javalin.Javalin;

public class App {

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
            config.enableDevLogging();
        });

        AppController appController = new AppController();

        app.ws("/chat/{id}", appController.chatController::connectToWebSocket);

        // Dependency injection for DAO and service layer
        UserDAO userDAOInt = new UserDAO();
        UserServiceInt userService = new UserService(userDAOInt);

        // User Routes
//        app.get("/user/{username}", UserController.getUserByUsername);

        app.start();


    }

}

