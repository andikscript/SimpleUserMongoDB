package com.andikscript.simpleusermongodb.controller;

import com.andikscript.simpleusermongodb.message.ResponseMessage;
import com.andikscript.simpleusermongodb.model.chat.Chat;
import com.andikscript.simpleusermongodb.model.chat.ChatUpdate;
import com.andikscript.simpleusermongodb.service.chat.ChatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<?> createChat(@RequestBody Chat chat) {
        chatService.createChat(chat);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage("create chat success"));
    }

    @PutMapping(value = "/update", consumes = "application/json")
    public ResponseEntity<?> updateChat(@RequestBody ChatUpdate chatUpdate) {
        chatService.updateChat(chatUpdate);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage("update success"));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllChat() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(chatService.getAllChat());
    }
}
