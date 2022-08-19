package com.andikscript.simpleusermongodb.service.chat;

import com.andikscript.simpleusermongodb.model.chat.Chat;
import com.andikscript.simpleusermongodb.model.chat.ChatUpdate;
import com.andikscript.simpleusermongodb.model.chat.Message;
import com.andikscript.simpleusermongodb.repository.chat.ChatRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ChatImpl implements ChatService {

    private final ChatRepository chatRepository;

    public ChatImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public void createChat(Chat chat) {
        chatRepository.save(chat);
    }

    @Override
    public void updateChat(ChatUpdate chatUpdate) {
        Optional<Chat> getChat = chatRepository.findByMember(chatUpdate.getMember());

        getChat.get().getMessage().add(new Message(chatUpdate.getBy(), chatUpdate.getText(),
                new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date())));
        chatRepository.save(getChat.get());
    }

    @Override
    public List<Chat> getAllChat() {
        return chatRepository.findAll();
    }
}
