package com.sovannarith.otp.exception;

import com.sovannarith.otp.model.response.ResponseMessage;
import com.sovannarith.otp.model.store.ResMsgStore;

public class TokenException extends Exception {

    private ResMsgStore resMsgStore;

    public TokenException() {
        super();
    }

    public TokenException(ResMsgStore resMsgStore) {
        super(resMsgStore.getMessage());
        this.resMsgStore = resMsgStore;
    }

    public ResponseMessage getResMsg(){
        ResponseMessage res = new ResponseMessage();
        res.setCode(resMsgStore.getCode());
        res.setMessage(resMsgStore.getMessage());
        return res;
    }

}
