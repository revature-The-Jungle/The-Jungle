package dev.com.thejungle.app.appcontroller.controllers;

import com.google.gson.Gson;
import dev.com.thejungle.customexception.InvalidInputException;
import dev.com.thejungle.customexception.UserNotFound;
import dev.com.thejungle.entity.User;
import dev.com.thejungle.service.implementations.UserService;
import dev.com.thejungle.service.interfaces.UserServiceInt;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserController {
    UserServiceInt userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    public Handler getUserByUsername = ctx -> {
        String username = ctx.pathParam("username");
        try {
            User user = this.userService.searchForUserService(username);
            Gson gson = new Gson();
            String userJSON = gson.toJson(user);
            ctx.result(userJSON);
            ctx.status(200);
        } catch (UserNotFound e) {
            ctx.result(e.getMessage());
            ctx.status(400);
        }
    };

    public Handler getAllUsers = ctx -> {
        try {
            List<User> users = this.userService.getAllUsersService();
            Gson gson = new Gson();
            String usersJSONs = gson.toJson(users);
            ctx.result(usersJSONs);
            ctx.status(200);
        } catch (UserNotFound e) {
            ctx.result(e.getMessage());
            ctx.status(400);
        }
    };

    public Handler loginUser = ctx -> {
        Gson gson = new Gson();
        Map<String, String> loginCredentials = gson.fromJson(ctx.body(), Map.class);
        try {
            User userLogin = this.userService.loginService(loginCredentials.get("username"),
                    loginCredentials.get("passcode"));
            String userLoginJSON = gson.toJson(userLogin);
            ctx.result(userLoginJSON);
            ctx.status(200);
        } catch (Exception e) {
            ctx.result(e.getMessage());
            ctx.status(400);
        }
    };

    public Handler getGroups = ctx -> {
        int userId = Integer.parseInt(ctx.pathParam("userId"));
        try {
            Gson gson = new Gson();
            Map<String, ArrayList> map = new HashMap<>();
            map.put("groupIds", this.userService.getGroups(userId));
            String resultsJson = gson.toJson(map);
            ctx.result(resultsJson);
            ctx.status(200);
        } catch (InvalidInputException e) {
            ctx.result(e.getMessage());
            ctx.status(400);
        }
    };
}
