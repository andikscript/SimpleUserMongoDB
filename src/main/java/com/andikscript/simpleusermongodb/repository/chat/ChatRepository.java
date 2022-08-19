package com.andikscript.simpleusermongodb.repository.chat;

import com.andikscript.simpleusermongodb.model.chat.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends MongoRepository<Chat, String> {

    Optional<Chat> findByMember(List member);
}
