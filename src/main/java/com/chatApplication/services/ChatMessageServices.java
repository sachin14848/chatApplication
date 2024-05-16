package com.chatApplication.services;

import com.chatApplication.model.ChatMessage;
import com.chatApplication.repo.ChatMessageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageServices {

    private final ChatMessageRepo messageRepo;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage){
        var chatId = chatRoomService.getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
                .orElseThrow();
        chatMessage.setChatId(chatId);
        messageRepo.save(chatMessage);
        return chatMessage;
    }


    public List<ChatMessage> findChatMessage(String senderId, String recipientId){
        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        return chatId.map(messageRepo::findByChatId).orElse(new ArrayList<>());
    }
}
