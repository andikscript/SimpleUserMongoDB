package com.andikscript.simpleusermongodb.queue;

import com.andikscript.simpleusermongodb.model.mail.Email;
import com.github.sonus21.rqueue.annotation.RqueueListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailQueue {

    private final JavaMailSender javaMailSender;

    public EmailQueue(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    private String sender;

    @RqueueListener("send-email")
    public void addQueueEmail(Email email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(email.getReceived());
        mailMessage.setSubject(email.getSubject());
        mailMessage.setText(email.getMessage());
        javaMailSender.send(mailMessage);
    }
}
