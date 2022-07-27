package com.andikscript.simpleusermongodb.repository;

import com.andikscript.simpleusermongodb.model.RefreshToken;
import com.andikscript.simpleusermongodb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshRepository extends MongoRepository<RefreshToken, String> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByIdUser(User user);
}
