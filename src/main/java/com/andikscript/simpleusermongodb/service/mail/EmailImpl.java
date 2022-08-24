package com.andikscript.simpleusermongodb.service.mail;

import com.andikscript.simpleusermongodb.model.mail.Email;
import com.github.sonus21.rqueue.core.RqueueMessageEnqueuer;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailImpl implements EmailService {

    private final RqueueMessageEnqueuer rqueueMessageEnqueuer;

    public EmailImpl(RqueueMessageEnqueuer rqueueMessageEnqueuer) {
        this.rqueueMessageEnqueuer = rqueueMessageEnqueuer;
    }
    @Override
    public void sendEmail(Email email) {
        rqueueMessageEnqueuer.enqueue("send-email", email);
    }
}
