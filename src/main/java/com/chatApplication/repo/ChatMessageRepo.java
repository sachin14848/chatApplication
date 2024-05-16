package com.chatApplication.repo;

import com.chatApplication.model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepo extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findByChatId(String chatId);
}
