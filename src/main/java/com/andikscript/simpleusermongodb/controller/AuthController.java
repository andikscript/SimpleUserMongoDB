package com.andikscript.simpleusermongodb.controller;

import com.andikscript.simpleusermongodb.handling.FailedValueBody;
import com.andikscript.simpleusermongodb.handling.RefreshTokenExpired;
import com.andikscript.simpleusermongodb.handling.UserAlready;
import com.andikscript.simpleusermongodb.message.ResponseMessage;
import com.andikscript.simpleusermongodb.model.RefreshToken;
import com.andikscript.simpleusermongodb.model.User;
import com.andikscript.simpleusermongodb.payload.JwtResponse;
import com.andikscript.simpleusermongodb.payload.RefreshTokenRequest;
import com.andikscript.simpleusermongodb.payload.RefreshTokenResponse;
import com.andikscript.simpleusermongodb.payload.UserPassRequest;
import com.andikscript.simpleusermongodb.security.jwt.JwtUtils;
import com.andikscript.simpleusermongodb.security.refresh.RefreshTokenService;
import com.andikscript.simpleusermongodb.security.service.UserDetailsImpl;
import com.andikscript.simpleusermongodb.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    private final UserService userService;

    private final JwtUtils jwtUtils;

    private final RefreshTokenService refreshTokenService;

    public AuthController(UserService userService,
                          JwtUtils jwtUtils, RefreshTokenService refreshTokenService) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping(value = "/signup", consumes = "application/json")
    public ResponseEntity<?> createUser(@RequestBody User user) throws FailedValueBody, UserAlready {
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage("Successfully create user"));
    }

    @PostMapping(value = "/signin", consumes = "application/json")
    public ResponseEntity<?> authUser(@Valid @RequestBody UserPassRequest userPassRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.authUser(userPassRequest));
    }

    @PostMapping(value = "/refreshtoken", consumes = "application/json")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest)
            throws RefreshTokenExpired {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.refreshToken(refreshTokenRequest));
    }

    @PostMapping(value = "/signout")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('ROOT')")
    public ResponseEntity<?> signout(@RequestHeader(value = "Authorization") String token) {
        String user = jwtUtils.getUsernameFromJwtToken(token.substring(7));
        refreshTokenService.deleteByUser(userService.getUserByUsername(user).get());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseMessage("Successfully signout"));
    }
}
