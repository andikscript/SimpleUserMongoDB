package com.andikscript.simpleusermongodb.service.user;

import com.andikscript.simpleusermongodb.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void createUser(User user);

    Optional<User> getUserByUsername(String username);
}
