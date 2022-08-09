package com.andikscript.simpleusermongodb.service.mail;

import com.andikscript.simpleusermongodb.repository.mail.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailImpl implements EmailService {

    private JavaMailSender javaMailSender;

    public EmailImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendEmail(Email email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(email.getReceived());
        mailMessage.setSubject(email.getSubject());
        mailMessage.setText(email.getMessage());

        javaMailSender.send(mailMessage);
        return "Mail sent success";
    }
}
