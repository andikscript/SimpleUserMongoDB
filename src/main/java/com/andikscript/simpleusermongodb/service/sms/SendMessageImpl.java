package com.andikscript.simpleusermongodb.service.sms;

import com.andikscript.simpleusermongodb.model.sms.SmsMessage;
import com.github.sonus21.rqueue.core.RqueueMessageEnqueuer;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SendMessageImpl implements SendMessageService {

    private final RqueueMessageEnqueuer rqueueMessageEnqueuer;

    public SendMessageImpl(RqueueMessageEnqueuer rqueueMessageEnqueuer) {
        this.rqueueMessageEnqueuer = rqueueMessageEnqueuer;
    }

    @Override
    public void sendMessage(String toPhone, String message) {
        SmsMessage sm = new SmsMessage();
        sm.setToPhone(toPhone);
        sm.setMessage(message);
        rqueueMessageEnqueuer.enqueue("send-sms", sm);
    }
}
