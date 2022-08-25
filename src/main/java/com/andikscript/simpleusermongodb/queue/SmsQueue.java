package com.andikscript.simpleusermongodb.queue;

import com.andikscript.simpleusermongodb.model.sms.SmsMessage;
import com.github.sonus21.rqueue.annotation.RqueueListener;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsQueue {

    @Value("${SendMessage.twilio.account.sid}")
    private String sid;

    @Value("${SendMessage.twilio.account.authtoken}")
    private String auth;

    @Value("${SendMessage.numberPhone}")
    private String fromPhone;

    @RqueueListener(value = "send-sms")
    public void sendQueueMessage(SmsMessage smsMessage) {
        Twilio.init(sid,auth);
        Message.creator(
                new PhoneNumber(smsMessage.getToPhone()),
                new PhoneNumber(fromPhone), smsMessage.getMessage()
        ).create();
    }
}
