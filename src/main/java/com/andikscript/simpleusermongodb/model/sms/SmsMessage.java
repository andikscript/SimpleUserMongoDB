package com.andikscript.simpleusermongodb.model.sms;

public class SmsMessage {

    private String toPhone;

    private String message;

    public String getToPhone() {
        return toPhone;
    }

    public void setToPhone(String toPhone) {
        this.toPhone = toPhone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
