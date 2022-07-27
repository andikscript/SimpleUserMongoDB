package com.andikscript.simpleusermongodb.controller;

import com.andikscript.simpleusermongodb.message.ResponseMessage;
import com.andikscript.simpleusermongodb.model.RefreshToken;
import com.andikscript.simpleusermongodb.model.User;
import com.andikscript.simpleusermongodb.payload.JwtResponse;
import com.andikscript.simpleusermongodb.payload.UserPassRequest;
import com.andikscript.simpleusermongodb.security.jwt.JwtUtils;
import com.andikscript.simpleusermongodb.security.refresh.RefreshTokenService;
import com.andikscript.simpleusermongodb.security.service.UserDetailsImpl;
import com.andikscript.simpleusermongodb.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final RefreshTokenService refreshTokenService;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager, JwtUtils jwtUtils,
                          RefreshTokenService refreshTokenService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping(value = "/signup", consumes = "application/json")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (user.getName() == null || user.getEmail() == null ||
        user.getUsername() == null || user.getPassword() == null ||
        user.getRoles().length == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage("Error"));
        }

        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage("Successfully create user"));
    }

    @PostMapping(value = "/signin", consumes = "application/json")
    public ResponseEntity<?> authUser(@RequestBody UserPassRequest userPassRequest) {
        if (userPassRequest.getUsername() == null || userPassRequest.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage("Error"));
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userPassRequest.getUsername(),
                        userPassRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new JwtResponse(
                        jwt, refreshToken.getToken(), userDetails.getUsername(),
                        userDetails.getPassword(), roles
                ));
    }
}
