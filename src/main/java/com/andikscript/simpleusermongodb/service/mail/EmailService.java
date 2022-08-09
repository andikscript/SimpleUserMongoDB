package com.andikscript.simpleusermongodb.service.mail;

import com.andikscript.simpleusermongodb.repository.mail.Email;

public interface EmailService {

    void sendEmail(Email email);
}
