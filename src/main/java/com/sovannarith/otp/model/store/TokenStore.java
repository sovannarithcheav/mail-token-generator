package com.sovannarith.otp.model.store;

import com.sovannarith.otp.model.Token;

import java.util.HashMap;
import java.util.Map;

public class TokenStore {

    private static Map<String, Token> map = new HashMap<>();

    public static void put(Token token){
        map.put(token.getId(), token);
    }

    public static Token get(String id){
        return map.get(id);
    }

    public static Map<String, Token> get(){
        return map;
    }

}
