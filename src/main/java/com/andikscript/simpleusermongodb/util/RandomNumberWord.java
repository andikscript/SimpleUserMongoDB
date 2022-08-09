package com.andikscript.simpleusermongodb.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomNumberWord {

    public String random() {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
                +"lmnopqrstuvwxyz!@#$%&";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(50);
        for (int i = 0; i < 50; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }
}
