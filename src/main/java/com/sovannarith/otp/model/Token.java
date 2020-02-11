package com.sovannarith.otp.model;

import com.sovannarith.otp.model.constant.Status;
import com.sovannarith.otp.util.TokenUtils;
import lombok.Data;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Data
public class Token {

    private String id;
    private String token;
    private String receiverMail;
    private Date expiredAt;
    private Date createdAt;
    private String status;

    public Token() {
        Calendar c = Calendar.getInstance();
        this.createdAt = c.getTime();
        c.add(Calendar.DATE, 1);
        this.expiredAt = c.getTime();
        this.status = Status.ACT;
        this.id = UUID.randomUUID().toString();
        this.token = Arrays.toString(TokenUtils.generateToken())
                .replace(",", "")
                .replace(" ", "")
                .replace("[", "")
                .replace("]", "").trim();
    }

}
