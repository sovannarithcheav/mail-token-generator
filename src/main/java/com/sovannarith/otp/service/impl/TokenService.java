package com.sovannarith.otp.service.impl;

import com.sovannarith.otp.exception.TokenException;
import com.sovannarith.otp.model.Token;
import com.sovannarith.otp.model.constant.Status;
import com.sovannarith.otp.model.request.TokenReq;
import com.sovannarith.otp.model.store.ResMsgStore;
import com.sovannarith.otp.model.store.TokenStore;
import com.sovannarith.otp.service.ITokenService;
import com.sovannarith.otp.util.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TokenService implements ITokenService {

    final JavaMailSender emailSender;
    final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    TokenService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public Token generate(TokenReq req) {
        Token token = new Token();
        token.setReceiverMail(req.getMail());

        TokenStore.get().values().stream().filter(t ->
                t.getStatus().equals(Status.ACT) && t.getReceiverMail().equals(req.getMail())
        ).forEach(t -> {
            t.setStatus(Status.DEL);
        });

        TokenStore.put(token);
        log.info("Token data saved {}", token);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(req.getMail());
        message.setSubject("Otp Testing");
        message.setText("Your otp code : " + token.getToken() + "\n" + "Expired at : " + df.format(token.getExpiredAt()));
        emailSender.send(message);
        return token;
    }

    @Override
    public Token delete(String id) throws TokenException {
        Token token = TokenStore.get(id);
        if (token == null) throw new TokenException(ResMsgStore.NOT_FOUND);
        token.setStatus(Status.DEL);
        TokenStore.put(token);
        return token;
    }

    @Override
    public Token get(String id) throws TokenException {
        Token token = TokenStore.get(id);
        if (token == null) throw new TokenException(ResMsgStore.NOT_FOUND);
        return token;
    }

    @Override
    public List<Token> get() throws TokenException {
        List<Token> ls = TokenStore.get().values().stream().collect(Collectors.toList());
        if (ls == null) throw new TokenException(ResMsgStore.NOT_FOUND);
        return ls;
    }

    @Override
    public Boolean verify(TokenReq token) throws TokenException {
        Optional<Token> existToken = TokenStore.get().values().stream()
                .filter(t ->
                        t.getStatus().equals(Status.ACT) && t.getToken().equals(token.getToken()) && t.getExpiredAt().after(new Date())
                ).findFirst();
        if (!existToken.isPresent())
            throw new TokenException(ResMsgStore.NOT_FOUND);

        Token t = existToken.get();
        t.setStatus(Status.USED);
        TokenStore.put(t);
        return true;
    }
}
