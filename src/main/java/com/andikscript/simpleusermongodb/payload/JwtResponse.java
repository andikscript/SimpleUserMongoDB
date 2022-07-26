package com.andikscript.simpleusermongodb.payload;

import java.util.List;

public class JwtResponse {

    private String accessToken;

    private String type = "Bearer";

    private String username;

    private String password;

    private List roles;

    public JwtResponse(String accessToken, String username, String password, List roles) {
        this.accessToken = accessToken;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List getRoles() {
        return roles;
    }
}
