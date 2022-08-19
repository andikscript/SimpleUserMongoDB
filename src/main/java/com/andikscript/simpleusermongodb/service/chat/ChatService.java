package com.andikscript.simpleusermongodb.service.chat;


import com.andikscript.simpleusermongodb.model.chat.Chat;
import com.andikscript.simpleusermongodb.model.chat.ChatUpdate;

import java.util.List;

public interface ChatService {

    void createChat(Chat chat);

    void updateChat(ChatUpdate chatUpdate);

    List<Chat> getAllChat();
}
