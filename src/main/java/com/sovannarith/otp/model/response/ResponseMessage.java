package com.sovannarith.otp.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sovannarith.otp.model.store.ResMsgStore;
import lombok.Data;

@Data
public class ResponseMessage {

    @JsonProperty("code")
    private String code;
    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private Object data;

    public ResponseMessage(){
        new ResponseMessage(null, ResMsgStore.SUCCESS);
    }

    public ResponseMessage(Object data, ResMsgStore r) {
        this.data = data;
        this.code = r.getCode();
        this.message = r.getMessage();
    }

    public static ResponseMessage $404() {
        return new ResponseMessage(null, ResMsgStore.NOT_FOUND);
    }

    public static ResponseMessage OK(Object data){
        return new ResponseMessage(data, ResMsgStore.SUCCESS);
    }

    public static ResponseMessage OK() {
        return OK(null);
    }
}
