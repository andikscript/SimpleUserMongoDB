package com.andikscript.simpleusermongodb.service.user;

import com.andikscript.simpleusermongodb.handling.*;
import com.andikscript.simpleusermongodb.model.mongo.User;
import com.andikscript.simpleusermongodb.payload.JwtResponse;
import com.andikscript.simpleusermongodb.payload.RefreshTokenRequest;
import com.andikscript.simpleusermongodb.payload.RefreshTokenResponse;
import com.andikscript.simpleusermongodb.payload.UserPassRequest;

import java.util.Optional;

public interface UserService {

    void createUser(User user) throws UserAlready;

    Optional<User> getUserByUsername(String username);

    void getVerify(UserPassRequest userPassRequest) throws UserNotConfirmed;

    JwtResponse authUser(String code) throws UserNotVerify;

    RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) throws RefreshTokenExpired;

    void findConfirmed(String confirmed) throws UserNotRegister;
}
