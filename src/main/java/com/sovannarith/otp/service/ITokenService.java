package com.sovannarith.otp.service;

import com.sovannarith.otp.exception.TokenException;
import com.sovannarith.otp.model.Token;
import com.sovannarith.otp.model.request.TokenReq;

import java.util.List;

public interface ITokenService {

    Token generate(TokenReq req);

    Token delete(String id) throws TokenException;

    Token get(String id) throws TokenException;

    List<Token> get() throws TokenException;

    Boolean verify(TokenReq token) throws TokenException;

}
