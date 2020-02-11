package com.sovannarith.otp.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class TokenUtils {

    public static char[] generateToken() {
        int len = 6;
        // Using numeric values
        String numbers = "0123456789";
        // Using random method
        Random rndm_method = new Random();
        char[] otp = new char[len];
        for (int i = 0; i < len; i++) {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            otp[i] =                    numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        log.info("your otp is {} ", otp);
        return otp;
    }


}
