package com.andikscript.simpleusermongodb.payload;

import java.util.List;

public class JwtResponse {

    private String accessToken;

    private String type = "Bearer";

    private String refreshToken;

    private String username;
    private List roles;

    public JwtResponse(String accessToken, String refreshToken, String username, List roles) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.username = username;
        this.roles = roles;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getType() {
        return type;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUsername() {
        return username;
    }

    public List getRoles() {
        return roles;
    }
}
