package dev.com.thejungle.app.app;

import dev.com.thejungle.app.appcontroller.appcontroller.AppController;
import dev.com.thejungle.app.appcontroller.controllers.UserController;
import dev.com.thejungle.dao.implementations.UserDAO;
import dev.com.thejungle.dao.interfaces.UserDAOInt;
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
        UserDAOInt userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        UserController userController = new UserController(userService);



        // User Routes
//        app.get("/user/{username}", UserController.getUserByUsername);
        app.post("/user/registration", userController.registerUser);

        app.start();


    }

}

