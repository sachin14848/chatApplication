package com.chatApplication.controller;

import com.chatApplication.model.User;
import com.chatApplication.services.UserServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequiredArgsConstructor
//@Slf4j
public class UserController {

    private final UserServices userServices;

    @MessageMapping("/user.addUser")
    @SendTo("/user/public")
    public User addUser(@Payload User user) {
        try {
            System.out.println(" This is add User Function " + user.toString());
            userServices.saveUser(user);
            return user;
        } catch (Exception ex) {
            System.err.println("Error processing message: " + ex.getMessage());
            throw new RuntimeException("Failed to process message", ex);
        }
//        logger.info("this is add user function ");

    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/public")
    public User disconnect(@Payload User user) {
        userServices.disconnect(user);
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findConnectedUser() {
        try {
            System.out.println("this is Find Connected user Function!");
            return ResponseEntity.ok(userServices.findConnectedUser());
        } catch (Exception ex) {
            throw new RuntimeException("Fail to fetch data");
        }
    }

}
