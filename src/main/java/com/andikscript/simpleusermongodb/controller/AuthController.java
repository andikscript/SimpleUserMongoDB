package com.andikscript.simpleusermongodb.controller;

import com.andikscript.simpleusermongodb.message.ResponseMessage;
import com.andikscript.simpleusermongodb.model.User;
import com.andikscript.simpleusermongodb.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/signup", consumes = "application/json")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (user.getName() == null || user.getEmail() == null ||
        user.getUsername() == null || user.getPassword() == null ||
        user.getRoles().length == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage("Error"));
        }

        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage("Successfully create user"));
    }
}
