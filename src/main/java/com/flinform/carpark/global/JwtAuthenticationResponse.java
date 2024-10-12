package com.flinform.carpark.global;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtAuthenticationResponse {

    @JsonProperty("token")
    private String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
