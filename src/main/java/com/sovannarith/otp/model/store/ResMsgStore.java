package com.sovannarith.otp.model.store;

import lombok.Data;

public enum  ResMsgStore {

    SUCCESS("S0001", "Successful"),
    NOT_FOUND("E0001", "Data not found"),
    TOKEN_EXPIRED("E0002", "Token expired");





    private String code;
    private String message;

    ResMsgStore(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode (){
        return this.code;
    }
    public String getMessage() {
        return this.message;
    }

}
