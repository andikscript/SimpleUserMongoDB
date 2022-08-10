package com.andikscript.simpleusermongodb.service.sms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SendMessageImpl implements SendMessageService {

    @Value("${SendMessage.twilio.account.sid}")
    private String sid;

    @Value("${SendMessage.twilio.account.authtoken}")
    private String auth;

    @Value("${SendMessage.numberPhone}")
    private String fromPhone;

    @Override
    public void sendMessage(String toPhone, String message) {
        Twilio.init(sid,auth);
        Message.creator(
                new PhoneNumber(toPhone),
                new PhoneNumber(fromPhone), message
        ).create();
    }
}
