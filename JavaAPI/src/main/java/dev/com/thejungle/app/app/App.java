package dev.com.thejungle.app.app;
import dev.com.thejungle.app.appcontroller.controllers.ChatController;
import io.javalin.Javalin;

public class App {

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
            config.enableDevLogging();
        });


        app.ws("/chat/{id}", ChatController::connectToWebSocket);

        app.start();


    }

}
