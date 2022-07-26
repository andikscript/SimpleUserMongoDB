package com.andikscript.simpleusermongodb.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/test")
public class TestController {

    @GetMapping(value = "/public")
    public String publicAccess() {
        return "public access";
    }

    @GetMapping(value = "/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('ROOT')")
    public String userAccess() {
        return "user access";
    }

    @GetMapping(value = "admin")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ROOT')")
    public String adminAccess() {
        return "admin access";
    }

    @GetMapping(value = "/root")
    @PreAuthorize("hasRole('ROOT')")
    public String rootAccess() {
        return "root access";
    }
}
