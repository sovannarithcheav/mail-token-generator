package com.sovannarith.otp.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TokenRes {

    @JsonProperty("token_ref_no")
    private String tokenRefNo;

}
