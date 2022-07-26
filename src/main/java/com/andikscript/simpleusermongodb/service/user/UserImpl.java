package com.andikscript.simpleusermongodb.service.user;

import com.andikscript.simpleusermongodb.model.User;
import com.andikscript.simpleusermongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserImpl implements UserService {

    private final UserRepository userRepository;

    public UserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAllUser() {
        return null;
    }

    @Override
    public void putUser(User user) {

    }

    @Override
    public void deleteUser(String id) {

    }
}
