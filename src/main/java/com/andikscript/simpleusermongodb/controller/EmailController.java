package com.andikscript.simpleusermongodb.controller;

import com.andikscript.simpleusermongodb.handling.UserNotRegister;
import com.andikscript.simpleusermongodb.message.ResponseMessage;
import com.andikscript.simpleusermongodb.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/email/")
public class EmailController {

    private final UserService userService;

    public EmailController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}/confirmed")
    public ResponseEntity<?> confirmedEmail(@PathVariable(value = "id") String confirmed) throws UserNotRegister {
        userService.findConfirmed(confirmed);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseMessage("confirmed"));
    }
}
