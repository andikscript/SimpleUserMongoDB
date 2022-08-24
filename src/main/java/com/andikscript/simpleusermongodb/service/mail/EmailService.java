package com.andikscript.simpleusermongodb.service.mail;

import com.andikscript.simpleusermongodb.model.mail.Email;

public interface EmailService {

    void sendEmail(Email email);
}
