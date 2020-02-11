package com.sovannarith.otp.controller;

import com.sovannarith.otp.exception.TokenException;
import com.sovannarith.otp.model.Token;
import com.sovannarith.otp.model.constant.Constant;
import com.sovannarith.otp.model.constant.Status;
import com.sovannarith.otp.model.request.TokenReq;
import com.sovannarith.otp.model.response.ResponseMessage;
import com.sovannarith.otp.model.response.TokenRes;
import com.sovannarith.otp.service.impl.TokenService;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class MainController {

    final TokenService tokenService;

    public MainController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping()
    public ResponseEntity<Object> generateOtp(@RequestBody TokenReq req) {
        Token token = tokenService.generate(req);
        TokenRes res = new TokenRes();
        res.setTokenRefNo(token.getId());
        return new ResponseEntity<>(ResponseMessage.OK(res), HttpStatus.OK);
    }

    @GetMapping(value = "/"+ Constant.KEY)
    public ResponseEntity<Object> getListOtp() {
        List<Token> token = null;
        try {
            token = tokenService.get();
            return new ResponseEntity<>(ResponseMessage.OK(token), HttpStatus.OK);
        } catch (TokenException e) {
            return new ResponseEntity<>(e.getResMsg(), HttpStatus.OK);
        }
    }


    @PostMapping("/verify")
    public ResponseEntity<Object> verify(@RequestBody TokenReq req) {
        Boolean bool = null;
        try {
            bool = tokenService.verify(req);
            if (bool) return new ResponseEntity<>(ResponseMessage.OK(), HttpStatus.OK);
        } catch (TokenException e) {
            return new ResponseEntity<>(e.getResMsg(), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseMessage.$404(), HttpStatus.OK);
    }

}
