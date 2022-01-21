package dev.com.thejungle.app.app;

import dev.com.thejungle.app.appcontroller.appcontroller.AppController;
import io.javalin.Javalin;

public class App {

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
            config.enableDevLogging();
        });

        AppController appController = new AppController();

        app.ws("/chat/{id}", appController.chatController::connectToWebSocket);

        app.start();


    }

}
