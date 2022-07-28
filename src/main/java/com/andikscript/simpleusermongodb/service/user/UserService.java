package com.andikscript.simpleusermongodb.service.user;

import com.andikscript.simpleusermongodb.handling.FailedValueBody;
import com.andikscript.simpleusermongodb.handling.RefreshTokenExpired;
import com.andikscript.simpleusermongodb.handling.UserAlready;
import com.andikscript.simpleusermongodb.model.User;
import com.andikscript.simpleusermongodb.payload.JwtResponse;
import com.andikscript.simpleusermongodb.payload.RefreshTokenRequest;
import com.andikscript.simpleusermongodb.payload.RefreshTokenResponse;
import com.andikscript.simpleusermongodb.payload.UserPassRequest;

import java.util.Optional;

public interface UserService {

    void createUser(User user) throws UserAlready;

    Optional<User> getUserByUsername(String username);

    JwtResponse authUser(UserPassRequest userPassRequest);

    RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) throws RefreshTokenExpired;
}
