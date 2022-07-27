package com.andikscript.simpleusermongodb.service.user;

import com.andikscript.simpleusermongodb.handling.FailedValueBody;
import com.andikscript.simpleusermongodb.handling.UserAlready;
import com.andikscript.simpleusermongodb.model.User;
import com.andikscript.simpleusermongodb.payload.JwtResponse;
import com.andikscript.simpleusermongodb.payload.UserPassRequest;

import java.util.Optional;

public interface UserService {

    void createUser(User user) throws FailedValueBody, UserAlready;

    Optional<User> getUserByUsername(String username);

    Optional<User> findByEmail(String email);

    JwtResponse authUser(UserPassRequest userPassRequest) throws FailedValueBody;
}
