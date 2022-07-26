package com.andikscript.simpleusermongodb.service.user;

import com.andikscript.simpleusermongodb.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void createUser(User user);

    Optional<User> getUserById(String id);

    List<User> getAllUser();

    void putUser(User user);

    void deleteUser(String id);
}
