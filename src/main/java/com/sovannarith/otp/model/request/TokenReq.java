package com.sovannarith.otp.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TokenReq {

    @JsonProperty
    private String token;
    @JsonProperty
    private String mail;
}
