package com.andikscript.simpleusermongodb.service.sms;

public interface SendMessageService {

    void sendMessage(String toPhone, String message);
}
