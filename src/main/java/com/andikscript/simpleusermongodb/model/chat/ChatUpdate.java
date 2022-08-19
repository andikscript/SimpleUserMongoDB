package com.andikscript.simpleusermongodb.model.chat;

import java.util.List;

public class ChatUpdate {

    private List member;

    private String by;

    private String text;

    public List getMember() {
        return member;
    }

    public void setMember(List member) {
        this.member = member;
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
}
