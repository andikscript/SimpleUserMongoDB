package com.andikscript.simpleusermongodb.controller;

import com.andikscript.simpleusermongodb.service.user.UserService;

public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
}
