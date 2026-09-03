package com.example.ticket;

import java.security.SecureRandom;

public class Util {

    public static String getUUid(){
        SecureRandom random = new SecureRandom();
        StringBuilder token = new StringBuilder();
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for(int i = 0; i < 20; i++) {
            int index = random.nextInt(chars.length());
            token.append(chars.charAt(index));
        }
        return token.toString();
    }
}
