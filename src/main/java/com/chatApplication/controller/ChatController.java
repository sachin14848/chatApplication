package com.chatApplication.controller;

import com.chatApplication.model.ChatMessage;
import com.chatApplication.model.ChatNotification;
import com.chatApplication.services.ChatMessageServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequiredArgsConstructor
//@Slf4j
public class ChatController {

//    private Logger logger;

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatMessageServices services;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        ChatMessage saveMSG = services.save(chatMessage);
        System.out.println("this chat function in " + chatMessage.toString());
//        logger.info(" this is MessageMapping "+ chatMessage.toString());
        simpMessagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(), "/queue/messages",
                new ChatNotification(
                        saveMSG.getId(),
                        saveMSG.getSenderId(),
                        saveMSG.getRecipientId(),
                        saveMSG.getContent()
                )
        );
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessage(@PathVariable String senderId, @PathVariable String recipientId) {
        return ResponseEntity.ok(services.findChatMessage(senderId, recipientId));
    }

}
