package com.chatApplication.repo;

import com.chatApplication.chatRoom.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatRoomRepo extends MongoRepository<ChatRoom, String > {
    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
