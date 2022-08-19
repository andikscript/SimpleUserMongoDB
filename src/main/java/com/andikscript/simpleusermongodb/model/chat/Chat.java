package com.andikscript.simpleusermongodb.model.chat;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "chat")
public class Chat {

    @Id
    private String id;

    private List member;

    private List message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List getMember() {
        return member;
    }

    public void setMember(List member) {
        this.member = member;
    }

    public List getMessage() {
        return message;
    }

    public void setMessage(List message) {
        this.message = message;
    }
}
