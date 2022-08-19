package com.andikscript.simpleusermongodb.model.chat;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Message {

    private String by;

    private String text;

    private String time;

    public Message(String by, String text, String time) {
        this.by = by;
        this.text = text;
        this.time = time;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
